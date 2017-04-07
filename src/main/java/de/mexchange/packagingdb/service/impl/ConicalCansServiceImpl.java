package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.ContainerTransformer;
import de.mexchange.packagingdb.domain.ConicalCans;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.lcp.ContainerType;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ConicalCansEntity;
import de.mexchange.packagingdb.entity.ContainerPhotoEntity;
import de.mexchange.packagingdb.repository.ConicalCansRepository;
import de.mexchange.packagingdb.repository.ContainerPhotoRepository;
import de.mexchange.packagingdb.repository.PackagingFormRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ConicalCansService;
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
public class ConicalCansServiceImpl extends AbstractService<ConicalCans, ConicalCansEntity> implements ConicalCansService {

    @Autowired
    private ConicalCansRepository repository;

    @Autowired
    private ContainerPhotoRepository containerPhotoRepository;

    @Autowired
    private PackagingFormRepository packagingFormRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalCans add(ConicalCans container) throws InternalErrorException {
        try {
            ConicalCansEntity entity = toEntity(container);
            entity.setPackagingFormEntity(packagingFormRepository.findOne(new Long(ContainerType.Conical_Cans.getValue())));
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Conical Cans object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void add(long containerID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException {
        ConicalCansEntity entity;
        try {
            entity = repository.findOne(containerID);
            if (entity != null) {
                ContainerPhotoEntity entityPhoto = ContainerTransformer.transform(photo);
                entity.addPhoto(entityPhoto);
                repository.saveAndFlush(entity);
            }
        } catch (Exception e) {
            throw new InternalErrorException();
        }

        if (entity == null) {
            throw new DataNotFoundException();
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
    public List<ConicalCans> getAll(Language language) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalCans edit(ConicalCans container) throws DataNotFoundException, InternalErrorException {
        ConicalCansEntity entity = repository.findOne(container.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(container), entity, "photos");
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public ConicalCans getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ConicalCansEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        ConicalCans container = ContainerTransformer.transform(entity, null);
        return container;
    }

    @Override
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deletePhotoByID(Long id) throws DataNotFoundException, InternalErrorException {
        Integer count;
        try {
            count = containerPhotoRepository.deletePhotoByID(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove ConicalCansPhoto [id: " + id + "]", e);
        }
        if (count == null || count != 1) {
            throw new DataNotFoundException("ConicalCansPhoto was not found [id: " + id + "]");
        }
    }

    @Override
    public List<ConicalCans> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ConicalCansEntity> data = repository.findAll(pageable);
            List<ConicalCansEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public ContainerPhoto getPhotoByID(Long id) throws DataNotFoundException, InternalErrorException {
        ContainerPhotoEntity entity;
        try {
            entity = containerPhotoRepository.getPhotoByID(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve ConicalCans photo [id:" + id + "]", e);
        }
        if (entity == null) {
            throw new DataNotFoundException("ConicalCans Photo [id:" + id + "] did not found");
        }

        ContainerPhoto photo = ContainerTransformer.transform(entity);
        return photo;
    }

	@Override
	public List<ConicalCans> typeahead(String info) throws InternalErrorException {
		return null;
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove ConicalCans objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public ConicalCansEntity toEntity(ConicalCans dto) {
        return ContainerTransformer.transform(dto);
    }

    @Override
    public ConicalCans fromEntity(ConicalCansEntity entity) {
        return ContainerTransformer.transform(entity);
    }

    @Override
    public ConicalCans fromEntity(ConicalCansEntity entity, Language language) {
        return ContainerTransformer.transform(entity, language);
    }
}
