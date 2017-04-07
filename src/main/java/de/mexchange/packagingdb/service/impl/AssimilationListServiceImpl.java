package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.AssimilationList;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnAssimilationListEntity;
import de.mexchange.packagingdb.repository.AssimilationListRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.AssimilationListService;
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
public class AssimilationListServiceImpl extends AbstractService<AssimilationList, UnAssimilationListEntity> implements AssimilationListService {

    @Autowired
    private AssimilationListRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public AssimilationList add(AssimilationList assimilationList) throws InternalErrorException {
        try {
            UnAssimilationListEntity entity = toEntity(assimilationList);
            return fromEntity(repository.save(entity));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add AssimilationList object", e);
        }
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
    public List<AssimilationList> getAll(Language language) {
        List<UnAssimilationListEntity> entities = repository.findAll();
        if(!CollectionUtils.isEmpty(entities)){
            return fromEntities(entities);
        }

        return null;
    }

    @Override
    public List<AssimilationList> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<UnAssimilationListEntity> data = repository.findAll(pageable);
            List<UnAssimilationListEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AssimilationList edit(AssimilationList assimilationList) throws InternalErrorException, DataNotFoundException {
        UnAssimilationListEntity entity = repository.findOne(assimilationList.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }

        // ignoring DataSource file not to be removed if exists
        String[] ignoredProperties = null;
        if (assimilationList.getDataSource() == null && StringHelper.isNotBlank(assimilationList.getDataSourceFilename())) {
            ignoredProperties = new String[] {
                    "dataSource",
                    "dataSourceFilename",
                    "dataSourceContentType"
            };
        }

        try {
            BeanUtils.copyProperties(toEntity(assimilationList), entity, ignoredProperties);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public AssimilationList getByID(Long id) throws DataNotFoundException, InternalErrorException {
        return fromEntity(repository.findOne(id));
    }

    @Override
    public List<AssimilationList> typeahead(String designation) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(designation));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve AssimilationList objects [designation:" + designation + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove AssimilationList objects [ID: " + id + "]", e);
        }
    }

    @Override
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove AssimilationList objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UnAssimilationListEntity toEntity(AssimilationList dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public AssimilationList fromEntity(UnAssimilationListEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public AssimilationList fromEntity(UnAssimilationListEntity entity, Language language) {
        return DataTransformer.transform(entity);
    }
}
