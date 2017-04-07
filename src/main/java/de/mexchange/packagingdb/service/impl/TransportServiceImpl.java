package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.Transport;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnTransportEntity;
import de.mexchange.packagingdb.repository.TransportRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.TransportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Arthur on 5/3/2016.
 */
@Service
@Transactional(readOnly = true)
public class TransportServiceImpl extends AbstractService<Transport, UnTransportEntity> implements TransportService {

    @Autowired
    private TransportRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Transport add(Transport transport) throws InternalErrorException {
        try {
            UnTransportEntity transportEntity = toEntity(transport);
            return fromEntity(repository.save(transportEntity));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Transport object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Transport edit(Transport transport) throws InternalErrorException, DataNotFoundException {
        UnTransportEntity entity = repository.findOne(transport.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(transport), entity);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Transport getByID(Long id) throws DataNotFoundException, InternalErrorException {
        return fromEntity(repository.findOne(id));
    }

    @Override
    public List<Transport> getAll(Language language) throws InternalErrorException {
        return null;
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
    public List<Transport> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<UnTransportEntity> data = repository.findAll(pageable);
            List<UnTransportEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Transport objects [ID: " + id + "]", e);
        }
    }

    @Override
    public List<Transport> typeahead(String verpGrp) throws InternalErrorException {
        try {
            return fromEntities(repository.typeahead(verpGrp));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve Transport objects [verpGrp:" + verpGrp + "]", e);
        }
    }

    @Override
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Transport objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UnTransportEntity toEntity(Transport dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Transport fromEntity(UnTransportEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Transport fromEntity(UnTransportEntity entity, Language language) {
        return DataTransformer.transform(entity);
    }

}
