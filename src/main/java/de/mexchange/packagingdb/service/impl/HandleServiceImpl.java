package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Handle;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.HandleEntity;
import de.mexchange.packagingdb.repository.HandleRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.HandleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Arthur on 5/3/2016.
 */
@Service
@Transactional(readOnly = true)
public class HandleServiceImpl extends AbstractService<Handle, HandleEntity> implements HandleService {

    @Autowired
    private HandleRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Handle add(Handle handle) throws InternalErrorException {
        try {
            HandleEntity handleEntity = toEntity(handle);
            return fromEntity(repository.save(handleEntity));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Handle object", e);
        }
    }

    @Override
    public List<Handle> getAll(Language language) throws InternalErrorException {
        List<HandleEntity> entities = repository.findAll();
        if(!CollectionUtils.isEmpty(entities)){
            return fromEntities(entities, language);
        }

        return null;
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
    public List<Handle> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<HandleEntity> data = repository.findAll(pageable);
            List<HandleEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Handle edit(Handle handle) throws InternalErrorException {
        HandleEntity entity = repository.findOne(handle.getId());

        /*if (entity == null) {
            throw new DataNotFoundException();
        }*/
        try {
            BeanUtils.copyProperties(toEntity(handle), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Handle getByID(Long id) throws DataNotFoundException, InternalErrorException {
        return fromEntity(repository.findOne(id));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Handle objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor =  Exception.class)
    public void deleteByIDs(List<Long> ids) throws InternalErrorException, DataNotFoundException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Handle objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }

    }

    @Override
    public List<Handle> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve Handle objects [name:" + info + "]", e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public HandleEntity toEntity(Handle dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Handle fromEntity(HandleEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Handle fromEntity(HandleEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
