package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.AggregateState;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.AggregateStateEntity;
import de.mexchange.packagingdb.repository.AggregateStateRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.AggregateStateService;
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
public class AggregateStateServiceImpl extends AbstractService<AggregateState, AggregateStateEntity> implements AggregateStateService {

    private AggregateStateRepository repository;

    @Autowired
    public AggregateStateServiceImpl(AggregateStateRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public AggregateState add(AggregateState aggregateState) throws InternalErrorException {
        try {
            AggregateStateEntity entity = toEntity(aggregateState);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Aggregate State object", e);
        }
    }

    @Override
    public AggregateState getByID(Long id) throws DataNotFoundException, InternalErrorException {
        AggregateStateEntity entity;
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
    public long getListCount() throws InternalErrorException {
        try {
            return repository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<AggregateState> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<AggregateState> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<AggregateStateEntity> data = repository.findAll(pageable);
            List<AggregateStateEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public AggregateState edit(AggregateState aggregateState) throws DataNotFoundException, InternalErrorException {
        AggregateStateEntity entity = repository.findOne(aggregateState.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(aggregateState), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<AggregateState> typeahead(String info) throws InternalErrorException {
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Aggregate State objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Aggregate State objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }


    //////////////////////////////////////////////////////////////
    @Override
    public AggregateStateEntity toEntity(AggregateState dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public AggregateState fromEntity(AggregateStateEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public AggregateState fromEntity(AggregateStateEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
