package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Location;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.LocationEntity;
import de.mexchange.packagingdb.repository.LocationRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LocationServiceImpl  extends AbstractService<Location, LocationEntity> implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public Location add(Location location) throws InternalErrorException {
        try {
            LocationEntity entity = toEntity(location);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add location object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public Location edit(Location location) throws DataNotFoundException, InternalErrorException {
        LocationEntity entity = repository.findOne(location.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(location), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Location getByID(Long id) throws DataNotFoundException, InternalErrorException {
        LocationEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        return fromEntity(entity);
    }

    @Override
    public List<Location> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public long getListCount() throws InternalErrorException {
        try {
            return repository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<Location> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<LocationEntity> data = repository.findAll(pageable);
            List<LocationEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Location> typeahead(String name) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(name));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Location objects [name:" + name + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove location objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove location objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////
    @Override
    public LocationEntity toEntity(Location dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Location fromEntity(LocationEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Location fromEntity(LocationEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
