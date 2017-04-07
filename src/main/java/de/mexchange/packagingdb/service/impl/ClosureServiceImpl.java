package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Closure;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ClosureEntity;
import de.mexchange.packagingdb.entity.CoatingEntity;
import de.mexchange.packagingdb.entity.GasketEntity;
import de.mexchange.packagingdb.entity.MaterialEntity;
import de.mexchange.packagingdb.repository.ClosureRepository;
import de.mexchange.packagingdb.repository.CoatingRepository;
import de.mexchange.packagingdb.repository.GasketRepository;
import de.mexchange.packagingdb.repository.MaterialRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ClosureService;
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
public class ClosureServiceImpl extends AbstractService<Closure, ClosureEntity> implements ClosureService {

    @Autowired
    private ClosureRepository repository;

    @Autowired
    private CoatingRepository coatingRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private GasketRepository gasketRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Closure add(Closure closure) throws InternalErrorException {
        try {
            ClosureEntity entity = toEntity(closure);

            if (entity.getInsideCoating() != null) {
                CoatingEntity coatingEntity = coatingRepository.findOne(entity.getInsideCoating().getId());
                entity.setInsideCoating(coatingEntity);
            }

            if (entity.getOutsideCoating() != null) {
                CoatingEntity coatingEntity = coatingRepository.findOne(entity.getOutsideCoating().getId());
                entity.setOutsideCoating(coatingEntity);
            }

            if (entity.getMaterial() != null) {
                MaterialEntity materialEntity = materialRepository.findOne(entity.getMaterial().getId());
                entity.setMaterial(materialEntity);
            }

            if (entity.getGasket() != null) {
                GasketEntity gasketEntity = gasketRepository.findOne(entity.getGasket().getId());
                entity.setGasket(gasketEntity);
            }

            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Gasket object", e);
        }
    }

    @Override
    public Closure getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ClosureEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        Closure coating = DataTransformer.transform(entity, null);
        return coating;
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
    public List<Closure> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Closure edit(Closure closure) throws DataNotFoundException, InternalErrorException {
        ClosureEntity entity = repository.findOne(closure.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(closure), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Closure> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ClosureEntity> data = repository.findAll(pageable);
            List<ClosureEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

	@Override
	public List<Closure> typeahead(String name) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(name));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve Closure objects [name:" + name + "]", e);
        }
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Closure objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Closure objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public ClosureEntity toEntity(Closure dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Closure fromEntity(ClosureEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Closure fromEntity(ClosureEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
