package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.search.FieldInfo;
import de.mexchange.packagingdb.common.search.ValueOption;
import de.mexchange.packagingdb.common.util.*;
import de.mexchange.packagingdb.common.util.transformer.ContainerTransformer;
import de.mexchange.packagingdb.domain.BigBags;
import de.mexchange.packagingdb.domain.CommonContainer;
import de.mexchange.packagingdb.domain.ConicalCanister;
import de.mexchange.packagingdb.entity.*;
import de.mexchange.packagingdb.repository.CompanyRepository;
import de.mexchange.packagingdb.repository.ContainerRepository;
import de.mexchange.packagingdb.repository.specification.ContainerSpecification;
import de.mexchange.packagingdb.service.ContainerService;
import de.mexchange.packagingdb.service.util.FieldsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Serozh on 7/10/2016.
 */
@Service
@Transactional(readOnly = true)
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public long getListCount(FieldInfo fieldInfo, ValueOption valueOption, String fieldValue) throws InternalErrorException {
        try {

            if (fieldInfo == null ||
                    valueOption == null ||
                    StringHelper.isBlank(fieldValue)){
                return containerRepository.count();
            }

            if (ContainerHelper.getInstance().isCommonField(fieldInfo.getName())){
                Specifications<ContainerEntity> spec = Specifications.where(
                        ContainerSpecification.filterField(fieldInfo,  valueOption,  fieldValue)
                );
                return containerRepository.count(spec);
            } else {
                List<String> containers = ContainerHelper.getInstance().getContainersContainingField(fieldInfo.getName());
                // query map
                Map queryMap = new HashMap<>();
                for (String container : containers) {
                    queryMap.put(container, MapUtil.map(fieldInfo.getName(), ContainerUtil.convert(container, fieldInfo.getName(), fieldValue)));
                }

                Object result = containerRepository.inheritedCountSearch(valueOption.getName(), queryMap);
                return result == null ? 0 : ((Long)result).longValue();
            }

        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<CommonContainer> search(int page, int count,
                                        FieldInfo fieldInfo,
                                        ValueOption valueOption,
                                        String fieldValue) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        List<CommonContainer> commonContainers = new ArrayList<>();

        try {
            //Page<ContainerEntity> data;
            List<ContainerEntity> data;
            if (fieldInfo == null ||
                    valueOption == null ||
                    StringHelper.isBlank(fieldValue)) {
                data = containerRepository.findAll(pageable).getContent();
            } else {

                if (ContainerHelper.getInstance().isCommonField(fieldInfo.getName())) {
                    Specifications<ContainerEntity> spec = Specifications.where(
                            ContainerSpecification.filterField(fieldInfo, valueOption, fieldValue)
                    );
                    data = containerRepository.findAll(spec, pageable).getContent();
                } else {

                    List<String> containers = ContainerHelper.getInstance().getContainersContainingField(fieldInfo.getName());
                    // query map
                    Map queryMap = new HashMap<>();
                    for (String container : containers) {
                        queryMap.put(container, MapUtil.map(fieldInfo.getName(), ContainerUtil.convert(container, fieldInfo.getName(), fieldValue)));
                    }

                    data = containerRepository.inheritedSearch(valueOption.getName(), queryMap);
                    if (CollectionHelper.isNotEmpty(data)) {
                        for (ContainerEntity containerEntity : data) {
                            if (containerEntity.getCompany() != null) {
                                containerEntity.setCompany(companyRepository.getOne(containerEntity.getCompany().getId()));
                            }
                        }
                    }
                }

            }
            commonContainers.addAll(fromEntities(data));
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        return commonContainers;
    }

    public List<CommonContainer> fromEntities(List<ContainerEntity> listEntities) {

        List<CommonContainer> containers = new ArrayList<>();

        if(CollectionHelper.isEmpty(listEntities)){
            return containers;
        }

        for (ContainerEntity entity : listEntities){
            containers.add(fromEntity(entity));
        }
        return containers;
    }

    public CommonContainer fromEntity(ContainerEntity entity) {

        CommonContainer container = new CommonContainer();

        //common fields
        container.setVpsNumber(entity.getId());
        container.setNominalVolume(entity.getNominalVolume());
        initContainerFileField(container, entity.getPhotos());
        container.setPackagingForm(entity.getClass().getSimpleName());
        container.setName(entity.getDesignation());
        //unknown fields
        container.setManufacturer("N/A");
        container.setApproval("N/A");

        if(entity instanceof BigBagsEntity){
            BigBagsEntity childEntity = (BigBagsEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setManufacturer(childEntity.getCompany().getName());
        } else if(entity instanceof BulkEntity){
            BulkEntity childEntity = (BulkEntity) entity;
            //container.setApproval(childEntity.getApproval());
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            if (!StringUtils.isEmpty(childEntity.getCompany())) {
                container.setManufacturer(childEntity.getCompany().getName());
            }
        } else if(entity instanceof ConicalCanisterEntity){
            ConicalCanisterEntity childEntity = (ConicalCanisterEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompany().getName());
        } else if(entity instanceof ConicalCansEntity){
            ConicalCansEntity childEntity = (ConicalCansEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompan().getName());
        } else if(entity instanceof ConicalClampingRingEntity){
            ConicalClampingRingEntity childEntity = (ConicalClampingRingEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompany().getName());
        } else if(entity instanceof CubicEntity){
            CubicEntity childEntity = (CubicEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompany().getName());
        } else if(entity instanceof CylindricalClampingRingEntity){
            CylindricalClampingRingEntity childEntity = (CylindricalClampingRingEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompany().getName());
        } else if(entity instanceof CylindricalEntity){
            CylindricalEntity childEntity = (CylindricalEntity) entity;
            //container.setApproval(childEntity.getBusinessUnit().getId()+"");
            //container.setApproval(childEntity.getApproval());
            //container.setManufacturer(childEntity.getCompany().getName());
        }

        return container;
    }

    private void initContainerFileField(CommonContainer container, Set<ContainerPhotoEntity> photoEntitySet){
        if(!CollectionHelper.isEmpty(photoEntitySet) && photoEntitySet.iterator().hasNext()){
            ContainerPhotoEntity photoEntity = photoEntitySet.iterator().next();
            container.setFileId(photoEntity.getId());
            container.setFileName(photoEntity.getFilename());
        }
    }
}
