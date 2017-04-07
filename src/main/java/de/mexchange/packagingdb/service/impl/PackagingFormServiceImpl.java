package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.PackagingForm;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.PackagingFormEntity;
import de.mexchange.packagingdb.repository.PackagingFormRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.PackagingFormService;
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
public class PackagingFormServiceImpl extends AbstractService<PackagingForm, PackagingFormEntity> implements PackagingFormService {

	private PackagingFormRepository repository;

	@Autowired
	public PackagingFormServiceImpl(PackagingFormRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public PackagingForm add(PackagingForm packagingForm) throws InternalErrorException {
		try {
			PackagingFormEntity entity = toEntity(packagingForm);
			entity = repository.save(entity);
			return fromEntity(entity);
		} catch (Exception e) {
			throw new InternalErrorException("Failed to add Packaging Form object", e);
		}
	}

	@Override
	public PackagingForm getByID(Long id) throws DataNotFoundException, InternalErrorException {
		PackagingFormEntity entity;
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
    public List<PackagingForm> getAll(Language language) {
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
	public List<PackagingForm> getList(int page, int count) throws InternalErrorException {
		Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<PackagingFormEntity> data = repository.findAll(pageable);
            List<PackagingFormEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public PackagingForm edit(PackagingForm packagingForm) throws DataNotFoundException, InternalErrorException {
		PackagingFormEntity entity = repository.findOne(packagingForm.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(packagingForm), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

    @Override
    public List<PackagingForm> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Packaging Form objects", e);
        }
    }

    @Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Packaging Form objects [ID: " + id + "]", e);
        }
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Packaging Form objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
             throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public PackagingFormEntity toEntity(PackagingForm dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public PackagingForm fromEntity(PackagingFormEntity entity) {
		return DataTransformer.transform(entity);
	}

    @Override
    public PackagingForm fromEntity(PackagingFormEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
