package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.Coupling;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CouplingEntity;
import de.mexchange.packagingdb.entity.GasketEntity;
import de.mexchange.packagingdb.entity.MaterialEntity;
import de.mexchange.packagingdb.repository.GasketRepository;
import de.mexchange.packagingdb.repository.CouplingRepository;
import de.mexchange.packagingdb.repository.MaterialRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.CouplingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CouplingServiceImpl extends AbstractService<Coupling, CouplingEntity> implements CouplingService {

    @Autowired
    private CouplingRepository couplingRepository;

    @Autowired
    private GasketRepository gasketRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Coupling add(Coupling coupling) throws InternalErrorException {
        try {
            CouplingEntity entity = toEntity(coupling);

            if (coupling.getMaterial() != null) {
                MaterialEntity materialEntity = materialRepository.findOne(coupling.getMaterial().getId());
                entity.setMaterial(materialEntity);
            }

            if (coupling.getGasket() != null) {
                GasketEntity gasketEntity = gasketRepository.findOne(coupling.getGasket().getId());
                entity.setGasket(gasketEntity);
            }

            entity = couplingRepository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to add Coupling object", e);
        }
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Coupling edit(Coupling coupling) throws InternalErrorException, DataNotFoundException {

        CouplingEntity entity = couplingRepository.findOne(coupling.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }

        // ignoring DataSource file not to be removed if exists
        List<String> ignoredProperties = new ArrayList<>();

        if (coupling.getImage() == null && StringHelper.isNotBlank(coupling.getImageFilename())) {

            String[] ignoredImage = new String[] {
                    "image",
                    "imageFilename",
                    "imageContentType"
            };
            ignoredProperties.addAll(Arrays.asList(ignoredImage));
        }

        if (coupling.getDrawing() == null  && StringHelper.isNotBlank(coupling.getDrawingFilename())) {

            String[] ignoredDrawing = new String[] {
                    "drawing",
                    "drawingFilename",
                    "drawingContentType"
            };
            ignoredProperties.addAll(Arrays.asList(ignoredDrawing));
        }

        try {
            BeanUtils.copyProperties(toEntity(coupling), entity, ignoredProperties.toArray(new String[ignoredProperties.size()]));
            entity = couplingRepository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Coupling getByID(Long id) throws DataNotFoundException, InternalErrorException {
        CouplingEntity entity;
        try {
            entity = couplingRepository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException();
        }
        Coupling coupling = DataTransformer.transform(entity, null);
        return coupling;
    }

    @Override
    public long getListCount() throws InternalErrorException {
        try {
            return couplingRepository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<Coupling> getAll(Language language) {
        return fromEntities(couplingRepository.findAll(), language);
    }

    @Override
    public List<Coupling> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<CouplingEntity> data = couplingRepository.findAll(pageable);
            List<CouplingEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Coupling> typeahead(String description) throws InternalErrorException {
        try {
            return fromEntities(couplingRepository.typeahead(description));
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve Coupling objects [description:" + description + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            couplingRepository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Coupling objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = couplingRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove Coupling objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public CouplingEntity toEntity(Coupling dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public Coupling fromEntity(CouplingEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public Coupling fromEntity(CouplingEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }
}
