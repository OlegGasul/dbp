package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.Coating;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CoatingEntity;
import de.mexchange.packagingdb.repository.CoatingRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.CoatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CoatingServiceImpl extends AbstractService<Coating, CoatingEntity> implements CoatingService {

    @Autowired
    private CoatingRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Coating add(Coating coating) throws InternalErrorException {
		CoatingEntity entity = toEntity(coating);
        entity = repository.save(entity);
		return fromEntity(entity);
    }

    @Override
    public Coating getByID(Long id) throws DataNotFoundException, InternalErrorException {
        CoatingEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        Coating coating = DataTransformer.transform(entity, null, true);
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
    public List<Coating> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<Coating> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<CoatingEntity> data = repository.findAll(pageable);
            List<CoatingEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Coating edit(Coating coating) throws DataNotFoundException, InternalErrorException {
        CoatingEntity entity = repository.findOne(coating.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        // ignoring certificate file not to be removed if exists
        String[] ignoredProperties = null;
        if (coating.getCertDFCF() == null && StringHelper.isNotBlank(coating.getCertDFCFilename())) {
            ignoredProperties = new String[] {
                    "certDFC",
                    "certDFCFilename",
                    "certDFCContentType"
            };
        }
        try {
            BeanUtils.copyProperties(toEntity(coating), entity, ignoredProperties);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Coating objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Coating objects [ID: " + id + "]", e);
        }
    }

	@Override
	public List<Coating> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve Coating objects [name:" + info + "]", e);
        }
	}

	////////////////////////////////////////////////////////////////////////////////
    @Override
    public CoatingEntity toEntity(Coating dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Coating fromEntity(CoatingEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Coating fromEntity(CoatingEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }

    @Override
    public List<Coating> fromEntities(List<CoatingEntity> entities) {
        List<Coating> dtoList = new ArrayList<>(entities.size());

        for (CoatingEntity entity : entities) {
            dtoList.add(DataTransformer.transform(entity, false));
        }

        return dtoList;
    }
}
