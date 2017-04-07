package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.ContainerTransformer;
import de.mexchange.packagingdb.domain.ConicalCanister;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.lcp.ContainerType;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ConicalCanisterEntity;
import de.mexchange.packagingdb.entity.ContainerPhotoEntity;
import de.mexchange.packagingdb.repository.ConicalCanisterRepository;
import de.mexchange.packagingdb.repository.ContainerPhotoRepository;
import de.mexchange.packagingdb.repository.PackagingFormRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ConicalCanisterService;
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
public class ConicalCanisterServiceImpl extends AbstractService<ConicalCanister, ConicalCanisterEntity> implements ConicalCanisterService {

    @Autowired
    private ConicalCanisterRepository repository;

    @Autowired
    private ContainerPhotoRepository containerPhotoRepository;

    @Autowired
    private PackagingFormRepository packagingFormRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalCanister add(ConicalCanister container) throws InternalErrorException {
        try {
            ConicalCanisterEntity entity = toEntity(container);
            entity.setPackagingFormEntity(packagingFormRepository.findOne(new Long(ContainerType.Canister_Cubic.getValue())));
            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Conical Canister object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void add(long containerID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException {
        ConicalCanisterEntity entity;
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
    public List<ConicalCanister> getAll(Language language) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public ConicalCanister edit(ConicalCanister container) throws DataNotFoundException, InternalErrorException {
        ConicalCanisterEntity entity = repository.findOne(container.getId());

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
    public ConicalCanister getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ConicalCanisterEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        ConicalCanister container = ContainerTransformer.transform(entity, null);
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
            throw new InternalErrorException("Failed to remove ConicalCanisterPhoto [id: " + id + "]", e);
        }
        if (count == null || count != 1) {
            throw new DataNotFoundException("ConicalCanisterPhoto was not found [id: " + id + "]");
        }
    }

    @Override
    public List<ConicalCanister> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ConicalCanisterEntity> data = repository.findAll(pageable);
            List<ConicalCanisterEntity> listEntities = data.getContent();
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
            throw new InternalErrorException("Failed to retrieve ConicalCanister photo [id:" + id + "]", e);
        }
        if (entity == null) {
            throw new DataNotFoundException("ConicalCanister Photo [id:" + id + "] did not found");
        }

        ContainerPhoto photo = ContainerTransformer.transform(entity);
        return photo;
    }

	@Override
	public List<ConicalCanister> typeahead(String info) throws InternalErrorException {
		return null;
	}

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove ConicalCanister objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public ConicalCanisterEntity toEntity(ConicalCanister dto) {
        return ContainerTransformer.transform(dto);
    }

    @Override
    public ConicalCanister fromEntity(ConicalCanisterEntity entity) {
        return ContainerTransformer.transform(entity);
    }

    @Override
    public ConicalCanister fromEntity(ConicalCanisterEntity entity, Language language) {
        return ContainerTransformer.transform(entity, language);
    }
}
