package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.UnTestMedium;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnTestMediumEntity;
import de.mexchange.packagingdb.repository.UnTestMediumRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.UnTestMediumService;
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
 * Created by Serozh on 6/15/16.
 */
@Service
@Transactional(readOnly = true)
public class UnTestMediumServiceImpl extends AbstractService<UnTestMedium, UnTestMediumEntity> implements UnTestMediumService {

	private UnTestMediumRepository repository;

	@Autowired
	public UnTestMediumServiceImpl(UnTestMediumRepository repository ) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public UnTestMedium add(UnTestMedium unTestMedium) throws InternalErrorException {
		try {
            UnTestMediumEntity entity = toEntity(unTestMedium);
			entity = repository.save(entity);
			return fromEntity(entity);
		} catch (Exception e) {
			throw new InternalErrorException("Failed to add UnTestMedium object", e);
		}
	}

    @Override
	public UnTestMedium getByID(Long id) throws DataNotFoundException, InternalErrorException {
        UnTestMediumEntity entity;
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
    public List<UnTestMedium> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<UnTestMedium> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<UnTestMediumEntity> data = repository.findAll(pageable);
            List<UnTestMediumEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public UnTestMedium edit(UnTestMedium unTestMedium) throws DataNotFoundException, InternalErrorException {
        UnTestMediumEntity entity = repository.findOne(unTestMedium.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(unTestMedium), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

    @Override
    public List<UnTestMedium> typeahead(String medium) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(medium));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve UnTestMedium objects [medium:" + medium + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove UnTestMedium objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove UnTestMedium objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
             throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public UnTestMediumEntity toEntity(UnTestMedium dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public UnTestMedium fromEntity(UnTestMediumEntity entity) {
		return DataTransformer.transform(entity);
	}

    @Override
    public UnTestMedium fromEntity(UnTestMediumEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
