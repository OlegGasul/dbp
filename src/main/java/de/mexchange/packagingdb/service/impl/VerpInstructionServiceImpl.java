package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.PackagingInstruction;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnPackagingInstructionEntity;
import de.mexchange.packagingdb.repository.PackagingInstructionRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.VerpInstructionService;
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
 * Created by Arthur on 5/3/2016.
 */
@Service
@Transactional(readOnly = true)
public class VerpInstructionServiceImpl extends AbstractService<PackagingInstruction, UnPackagingInstructionEntity> implements VerpInstructionService {

    @Autowired
    private PackagingInstructionRepository packagingInstructionRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public PackagingInstruction add(PackagingInstruction packagingInstruction) throws InternalErrorException {
        try {
            UnPackagingInstructionEntity entity = toEntity(packagingInstruction);
            return fromEntity(packagingInstructionRepository.save(entity));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add PackagingInstruction object", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public PackagingInstruction edit(PackagingInstruction packagingInstruction) throws InternalErrorException, DataNotFoundException {
        UnPackagingInstructionEntity entity = packagingInstructionRepository.findOne(packagingInstruction.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(packagingInstruction), entity);
            entity = packagingInstructionRepository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public PackagingInstruction getByID(Long id) throws DataNotFoundException, InternalErrorException {
        return fromEntity(packagingInstructionRepository.findOne(id));
    }

    @Override
    public List<PackagingInstruction> getAll(Language language) throws InternalErrorException {
        return null;
    }

    @Override
    public long getListCount() throws InternalErrorException {
        try {
            return packagingInstructionRepository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<PackagingInstruction> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<UnPackagingInstructionEntity> data = packagingInstructionRepository.findAll(pageable);
            List<UnPackagingInstructionEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<PackagingInstruction> typeahead(String transportLaw) throws InternalErrorException {
        try {
            return fromEntities(packagingInstructionRepository.typeahead(transportLaw));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve PackagingInstruction objects [transportLaw:" + transportLaw + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            packagingInstructionRepository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove PackagingInstruction objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = packagingInstructionRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove Verp Instruction objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UnPackagingInstructionEntity toEntity(PackagingInstruction dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public PackagingInstruction fromEntity(UnPackagingInstructionEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public PackagingInstruction fromEntity(UnPackagingInstructionEntity entity, Language language) {
        return DataTransformer.transform(entity);
    }

}
