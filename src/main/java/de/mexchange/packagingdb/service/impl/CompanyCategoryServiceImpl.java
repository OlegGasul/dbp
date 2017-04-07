package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.CompanyCategory;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CompanyCategoryEntity;
import de.mexchange.packagingdb.repository.CompanyCategoryRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.CompanyCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arthur on 5/19/2016.
 */
@Service
@Transactional(readOnly = true)
public class CompanyCategoryServiceImpl extends AbstractService<CompanyCategory, CompanyCategoryEntity> implements CompanyCategoryService {

    @Autowired
    private CompanyCategoryRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public CompanyCategory add(CompanyCategory companyCategory) throws InternalErrorException {
        try {
            CompanyCategoryEntity entity = toEntity(companyCategory);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Company Category object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public CompanyCategory edit(CompanyCategory companyCategory) throws DataNotFoundException, InternalErrorException {
        CompanyCategoryEntity entity = repository.findOne(companyCategory.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(companyCategory), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CompanyCategory getByID(Long id) throws DataNotFoundException, InternalErrorException {
        CompanyCategoryEntity entity;
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
    public List<CompanyCategory> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<CompanyCategory> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<CompanyCategoryEntity> data = repository.findAll(pageable);
            List<CompanyCategoryEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CompanyCategory> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Company Category objects", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Company Category objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Company Category objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public CompanyCategoryEntity toEntity(CompanyCategory dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public CompanyCategory fromEntity(CompanyCategoryEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public CompanyCategory fromEntity(CompanyCategoryEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
