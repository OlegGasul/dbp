package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.ContainerTransformer;
import de.mexchange.packagingdb.domain.ConicalClampingRing;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.lcp.ContainerType;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ConicalClampingRingEntity;
import de.mexchange.packagingdb.entity.ContainerPhotoEntity;
import de.mexchange.packagingdb.repository.ConicalClampingRingRepository;
import de.mexchange.packagingdb.repository.ContainerPhotoRepository;
import de.mexchange.packagingdb.repository.PackagingFormRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ConicalClampingRingsService;
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
public class ConicalClampingRingsServiceImpl extends AbstractService<ConicalClampingRing, ConicalClampingRingEntity> implements ConicalClampingRingsService {

    @Autowired
    private ConicalClampingRingRepository repository;

    @Autowired
    private ContainerPhotoRepository containerPhotoRepository;

    @Autowired
    private PackagingFormRepository packagingFormRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalClampingRing add(ConicalClampingRing container) throws InternalErrorException {
        try {
            ConicalClampingRingEntity entity = toEntity(container);
            entity.setPackagingFormEntity(packagingFormRepository.findOne(new Long(ContainerType.Conical_Clamping_Ring.getValue())));
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add ConicalClampingRing object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void add(long containerID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException {
        ConicalClampingRingEntity entity;
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
    public List<ConicalClampingRing> getAll(Language language) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalClampingRing edit(ConicalClampingRing container) throws DataNotFoundException, InternalErrorException {
        ConicalClampingRingEntity entity = repository.findOne(container.getId());

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
    public ConicalClampingRing getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ConicalClampingRingEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        ConicalClampingRing container = ContainerTransformer.transform(entity, null);
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
            throw new InternalErrorException("Failed to remove ConicalClampingRingPhoto [id: " + id + "]", e);
        }
        if (count == null || count != 1) {
            throw new DataNotFoundException("ConicalClampingRingPhoto was not found [id: " + id + "]");
        }
    }

    @Override
    public List<ConicalClampingRing> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ConicalClampingRingEntity> data = repository.findAll(pageable);
            List<ConicalClampingRingEntity> listEntities = data.getContent();
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
            throw new InternalErrorException("Failed to retrieve ConicalClampingRing photo [id:" + id + "]", e);
        }
        if (entity == null) {
            throw new DataNotFoundException("ConicalClampingRing Photo [id:" + id + "] did not found");
        }

        ContainerPhoto photo = ContainerTransformer.transform(entity);
        return photo;
    }

	@Override
	public List<ConicalClampingRing> typeahead(String info) throws InternalErrorException {
		return null;
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove ConicalClampingRing objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public ConicalClampingRingEntity toEntity(ConicalClampingRing dto) {
        return ContainerTransformer.transform(dto);
    }

    @Override
    public ConicalClampingRing fromEntity(ConicalClampingRingEntity entity) {
        return ContainerTransformer.transform(entity);
    }

    @Override
    public ConicalClampingRing fromEntity(ConicalClampingRingEntity entity, Language language) {
        return ContainerTransformer.transform(entity, language);
    }
}
