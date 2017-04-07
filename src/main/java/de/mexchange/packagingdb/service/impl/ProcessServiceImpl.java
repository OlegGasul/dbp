package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.Process;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.ProcessType;
import de.mexchange.packagingdb.entity.ProcessEntity;
import de.mexchange.packagingdb.repository.ProcessRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.ProcessService;
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
public class ProcessServiceImpl extends AbstractService<Process, ProcessEntity> implements ProcessService {

    @Autowired
    private ProcessRepository repository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Process add(Process process) throws InternalErrorException {
        ProcessEntity entity = toEntity(process);
        entity = repository.save(entity);
        return fromEntity(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Process edit(Process process) throws DataNotFoundException, InternalErrorException {
        ProcessEntity entity = repository.findOne(process.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        // ignoring certificate file not to be removed if exists
        String[] ignoredProperties = null;
        if (process.getProcessFile() == null && StringHelper.isNotBlank(process.getProcessFilename())) {
            ignoredProperties = new String[] {
                    "processFile",
                    "processFilename",
                    "processFileContentType"
            };
        }
        try {
            BeanUtils.copyProperties(toEntity(process), entity, ignoredProperties);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Process getByID(Long id) throws DataNotFoundException, InternalErrorException {
        ProcessEntity entity;
        try {
            entity = repository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        Process process = DataTransformer.transform(entity, null, true);
        return process;
    }

    @Override
    public List<Process> getAll(Language language) {
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
    public List<Process> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ProcessEntity> data = repository.findAll(pageable);
            List<ProcessEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Process> typeahead(String info) throws InternalErrorException {
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Process objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Process objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    @Override
    public List<Process> getList(int page, ProcessType type) throws InternalErrorException {
        return getList(page, 10, type);
    }

    @Override
    public List<Process> getList(int page, int count, ProcessType type) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<ProcessEntity> data = repository.findByProcessType(pageable, type);
            List<ProcessEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public long getListCount(ProcessType type) throws InternalErrorException {
        try {
            return repository.countByProcessType(type);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public ProcessEntity toEntity(Process dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Process fromEntity(ProcessEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Process fromEntity(ProcessEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }

    @Override
    public List<Process> fromEntities(List<ProcessEntity> entities) {
        List<Process> dtoList = new ArrayList<>(entities.size());

        for (ProcessEntity entity : entities) {
            dtoList.add(DataTransformer.transform(entity, false));
        }

        return dtoList;
    }
}
