package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.ExZone;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ExZoneEntity;
import de.mexchange.packagingdb.repository.ExZoneRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ExZoneService;
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
public class ExZoneServiceImpl extends AbstractService<ExZone, ExZoneEntity> implements ExZoneService {

    private ExZoneRepository repository;

    @Autowired
    public ExZoneServiceImpl(ExZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ExZone add(ExZone exZone) throws InternalErrorException {
        try {
            ExZoneEntity entity = toEntity(exZone);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Aggregate State object", e);
        }
    }

    @Override
    public ExZone getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ExZoneEntity entity;
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
    public List<ExZone> getAll(Language language) {
        // TODO fetch only for current language
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
    public List<ExZone> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ExZoneEntity> data = repository.findAll(pageable);
            List<ExZoneEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ExZone edit(ExZone exZone) throws DataNotFoundException, InternalErrorException {
        ExZoneEntity entity = repository.findOne(exZone.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(exZone), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ExZone> typeahead(String name) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(name));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve ExZone objects [name:" + name + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove ExZone objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove ExZone objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    //////////////////////////////////////////////////////////////
    @Override
    public ExZoneEntity toEntity(ExZone dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public ExZone fromEntity(ExZoneEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public ExZone fromEntity(ExZoneEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
