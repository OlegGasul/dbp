package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.PaletteType;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.PaletteTypeEntity;
import de.mexchange.packagingdb.repository.PaletteTypeRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.PaletteTypeService;
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
 * Created by Garik on 4/30/16.
 */
@Service
@Transactional(readOnly = true)
public class PaletteTypeServiceImpl extends AbstractService<PaletteType, PaletteTypeEntity> implements PaletteTypeService {

	private PaletteTypeRepository repository;

	@Autowired
	public PaletteTypeServiceImpl(PaletteTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public PaletteType add(PaletteType paletteType) throws InternalErrorException {
		try {
			PaletteTypeEntity entity = toEntity(paletteType);
			entity = repository.save(entity);
			return fromEntity(entity);
		} catch (Exception e) {
			throw new InternalErrorException("Failed to add Palette Type object", e);
		}
	}

	@Override
	public PaletteType getByID(Long id) throws DataNotFoundException, InternalErrorException {
		PaletteTypeEntity entity;
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
    public List<PaletteType> getAll(Language language) {
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
	public List<PaletteType> getList(int startPage, int count) throws InternalErrorException {
		Pageable pageable = new PageRequest(startPage, count, Sort.Direction.ASC, "id");
        try {
            Page<PaletteTypeEntity> data = repository.findAll(pageable);
            List<PaletteTypeEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public PaletteType edit(PaletteType businessUnit) throws DataNotFoundException, InternalErrorException {
		PaletteTypeEntity entity = repository.findOne(businessUnit.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(businessUnit), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

    @Override
    public List<PaletteType> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Palette Type objects", e);
        }
    }

    @Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove palette type objects [ID: " + id + "]", e);
        }
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove palette type objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
             throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public PaletteTypeEntity toEntity(PaletteType dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public PaletteType fromEntity(PaletteTypeEntity entity) {
		return DataTransformer.transform(entity);
	}

    @Override
    public PaletteType fromEntity(PaletteTypeEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
