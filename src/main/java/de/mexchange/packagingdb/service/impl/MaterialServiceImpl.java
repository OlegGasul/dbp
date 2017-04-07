package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Material;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.MaterialEntity;
import de.mexchange.packagingdb.repository.MaterialRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.MaterialService;
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
public class MaterialServiceImpl extends AbstractService<Material, MaterialEntity> implements MaterialService {

    @Autowired
    private MaterialRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Material add(Material material) throws InternalErrorException {
        try {
            MaterialEntity entity = toEntity(material);
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add material object", e);
        }
    }

    @Override
    public Material getByID(Long id) throws InternalErrorException, DataNotFoundException {
        MaterialEntity entity;
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
    public List<Material> getAll(Language language) {
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
    public List<Material> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<MaterialEntity> data = repository.findAll(pageable);
            List<MaterialEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public Material edit(Material material) throws InternalErrorException, DataNotFoundException {
        MaterialEntity entity = repository.findOne(material.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(material), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Material> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve Material objects [name:" + info + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Material objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Material objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public MaterialEntity toEntity(Material dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Material fromEntity(MaterialEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Material fromEntity(MaterialEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
