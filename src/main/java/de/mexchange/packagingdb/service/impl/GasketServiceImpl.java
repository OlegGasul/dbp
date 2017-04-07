package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Gasket;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.GasketEntity;
import de.mexchange.packagingdb.entity.MaterialEntity;
import de.mexchange.packagingdb.repository.GasketRepository;
import de.mexchange.packagingdb.repository.MaterialRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.GasketService;
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
public class GasketServiceImpl extends AbstractService<Gasket, GasketEntity> implements GasketService {

	private GasketRepository repository;

	private MaterialRepository materialRepository;

	@Autowired
	public GasketServiceImpl(GasketRepository repository, MaterialRepository materialRepository) {
		this.repository = repository;
        this.materialRepository = materialRepository;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public Gasket add(Gasket gasket) throws InternalErrorException {
		try {
			GasketEntity entity = toEntity(gasket);
            if (entity.getMaterial() != null) {
                MaterialEntity materialEntity = materialRepository.findOne(entity.getMaterial().getId());
                entity.setMaterial(materialEntity);
            }
			entity = repository.save(entity);
			return fromEntity(entity);
		} catch (Exception e) {
			throw new InternalErrorException("Failed to add Gasket object", e);
		}
	}

    @Override
	public Gasket getByID(Long id) throws DataNotFoundException, InternalErrorException {
		GasketEntity entity;
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
    public List<Gasket> getAll(Language language) {
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
	public List<Gasket> getList(int page, int count) throws InternalErrorException {
		Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<GasketEntity> data = repository.findAll(pageable);
            List<GasketEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public Gasket edit(Gasket gasket) throws DataNotFoundException, InternalErrorException {
		GasketEntity entity = repository.findOne(gasket.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(gasket), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
	}

    @Override
    public List<Gasket> typeahead(String desc) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(desc));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Gasket objects [desc:" + desc + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Gasket objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Gasket objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
             throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public GasketEntity toEntity(Gasket dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public Gasket fromEntity(GasketEntity entity) {
		return DataTransformer.transform(entity);
	}

    @Override
    public Gasket fromEntity(GasketEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
