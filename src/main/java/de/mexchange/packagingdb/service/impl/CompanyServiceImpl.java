package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Company;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CompanyEntity;
import de.mexchange.packagingdb.repository.CompanyRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.CompanyService;
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
public class CompanyServiceImpl extends AbstractService<Company, CompanyEntity> implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Company add(Company company) throws InternalErrorException {
        try {
            CompanyEntity entity = toEntity(company);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Company object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Company edit(Company company) throws DataNotFoundException, InternalErrorException {
        CompanyEntity entity = repository.findOne(company.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(company), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Company getByID(Long id) throws DataNotFoundException, InternalErrorException {
        CompanyEntity entity;
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
    public List<Company> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<Company> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<CompanyEntity> data = repository.findAll(pageable);
            List<CompanyEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long Id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(Id);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to delete Company objects", e);
        }
    }

    @Override
    public List<Company> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Company Category objects", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Company objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }
    /////////////////////////////////////////////////////////////
    @Override
    public CompanyEntity toEntity(Company dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Company fromEntity(CompanyEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Company fromEntity(CompanyEntity entity, Language language) {
        return DataTransformer.transform(entity);
    }
}
