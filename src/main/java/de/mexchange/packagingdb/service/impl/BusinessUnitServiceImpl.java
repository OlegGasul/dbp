package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.BusinessUnit;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.BusinessUnitEntity;
import de.mexchange.packagingdb.repository.BusinessUnitRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.BusinessUnitService;
import de.mexchange.packagingdb.service.MessageService;
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
public class BusinessUnitServiceImpl extends AbstractService<BusinessUnit, BusinessUnitEntity> implements BusinessUnitService {

	private BusinessUnitRepository repository;

	private MessageService messageService;

	@Autowired
	public BusinessUnitServiceImpl(BusinessUnitRepository repository, MessageService messageService) {
		this.repository = repository;
        this.messageService = messageService;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public BusinessUnit add(BusinessUnit businessUnit) throws InternalErrorException {
		try {
			BusinessUnitEntity entity = toEntity(businessUnit);
			entity = repository.save(entity);
			return fromEntity(entity);
		} catch (Exception e) {
			throw new InternalErrorException("Failed to add BusinessUnit object", e);
		}
	}

	@Override
	public BusinessUnit getByID(Long id) throws DataNotFoundException, InternalErrorException {
		BusinessUnitEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
             throw new InternalErrorException(e);
        }
        if (entity == null) {
             throw new DataNotFoundException(messageService.getMessage("business.unit.not.found", id));
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
    public List<BusinessUnit> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
	public List<BusinessUnit> getList(int page, int count) throws InternalErrorException {
		Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<BusinessUnitEntity> data = repository.findAll(pageable);
            List<BusinessUnitEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public BusinessUnit edit(BusinessUnit businessUnit) throws DataNotFoundException, InternalErrorException {
		BusinessUnitEntity entity = repository.findOne(businessUnit.getId());

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
    public List<BusinessUnit> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve BU objects", e);
        }
    }

    @Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove BU objects [ID: " + id + "]", e);
        }
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove BU objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
             throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public BusinessUnitEntity toEntity(BusinessUnit dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public BusinessUnit fromEntity(BusinessUnitEntity entity) {
		return DataTransformer.transform(entity);
	}

    @Override
    public BusinessUnit fromEntity(BusinessUnitEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
