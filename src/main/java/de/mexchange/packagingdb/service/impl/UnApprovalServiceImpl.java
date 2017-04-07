package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.UnApproval;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.*;
import de.mexchange.packagingdb.repository.*;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.UnApprovalService;
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
public class UnApprovalServiceImpl extends AbstractService<UnApproval, UnApprovalEntity> implements UnApprovalService {

    private UnApprovalRepository repository;

    private UnTestMediumRepository unTestMediumRepository;

    private CompanyRepository companyRepository;

    private AssimilationListRepository assimilationListRepository;

    private PackagingInstructionRepository packagingInstructionRepository;

    private TransportRepository transportRepository;

    @Autowired
    public UnApprovalServiceImpl(UnApprovalRepository repository, UnTestMediumRepository unTestMediumRepository, CompanyRepository companyRepository, AssimilationListRepository assimilationListRepository, PackagingInstructionRepository packagingInstructionRepository, TransportRepository transportRepository ) {
        this.repository = repository;
        this.unTestMediumRepository = unTestMediumRepository;
        this.companyRepository = companyRepository;
        this.assimilationListRepository = assimilationListRepository;
        this.packagingInstructionRepository = packagingInstructionRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public UnApproval add(UnApproval unApproval) throws InternalErrorException {
        try {
            UnApprovalEntity entity = toEntity(unApproval);
            if (entity.getTestMedium() != null) {
                UnTestMediumEntity materialEntity = unTestMediumRepository.findOne(entity.getTestMedium().getId());
                entity.setTestMedium(materialEntity);
            }

            if (entity.getCompany() != null) {
                CompanyEntity companyEntity = companyRepository.findOne(entity.getCompany().getId());
                entity.setCompany(companyEntity);
            }

            if (entity.getAssimilationList() != null) {
                UnAssimilationListEntity assimilationListRepositoryOne = assimilationListRepository.findOne(entity.getAssimilationList().getId());
                entity.setAssimilationList(assimilationListRepositoryOne);
            }

            if (entity.getVerpInstruction() != null) {
                UnPackagingInstructionEntity unPackagingInstructionEntity = packagingInstructionRepository.findOne(entity.getVerpInstruction().getId());
                entity.setVerpInstruction(unPackagingInstructionEntity);
            }

            if (entity.getTransport() != null) {
                UnTransportEntity transportEntity = transportRepository.findOne(entity.getTransport().getId());
                entity.setTransport(transportEntity);
            }

            entity = repository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add UnApproval object", e);
        }
    }

    @Override
    public UnApproval getByID(Long id) throws DataNotFoundException, InternalErrorException {
        UnApprovalEntity entity;
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
    public List<UnApproval> getAll(Language language) {
        return fromEntities(repository.findAll(), language);
    }

    @Override
    public List<UnApproval> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<UnApprovalEntity> data = repository.findAll(pageable);
            List<UnApprovalEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public UnApproval edit(UnApproval unApproval) throws DataNotFoundException, InternalErrorException {
        UnApprovalEntity entity = repository.findOne(unApproval.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }

        // ignoring certificate file not to be removed if exists
        String[] ignoredProperties = null;
        if (unApproval.getFile() == null && StringHelper.isNotBlank(unApproval.getFileFilename())) {
            ignoredProperties = new String[] {
                    "file",
                    "fileFilename",
                    "fileContentType"
            };
        }

        try {
            BeanUtils.copyProperties(toEntity(unApproval), entity, ignoredProperties);
            entity = repository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<UnApproval> typeahead(String desc) throws InternalErrorException {
        /*try {
            return fromEntities(repository.typeahead(desc));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve UnApproval objects [desc:" + desc + "]", e);
        }*/
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove UnApproval objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = repository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove UnApproval objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public UnApprovalEntity toEntity(UnApproval dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public UnApproval fromEntity(UnApprovalEntity entity) {
		return DataTransformer.transform(entity);
    }

    @Override
    public UnApproval fromEntity(UnApprovalEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
