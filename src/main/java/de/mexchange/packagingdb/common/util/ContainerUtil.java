package de.mexchange.packagingdb.common.util;

import de.mexchange.packagingdb.common.exception.ObjectConvertException;
import de.mexchange.packagingdb.domain.lcp.ContainerStatus;
import de.mexchange.packagingdb.domain.lcp.ContainerType;
import de.mexchange.packagingdb.entity.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 7/13/2016.
 */
public class ContainerUtil {

    public static Object convert(String container, String fieldName, String value) throws Exception {
        Class clazz = ReflationUtil.getFieldType(container, fieldName);
        if (clazz.equals(java.lang.String.class)) return value;
        if (clazz.equals(java.lang.Double.class)) return Double.valueOf(value);
        if (clazz.equals(java.lang.Integer.class)) return Integer.valueOf(value);
        if (clazz.equals(java.lang.Long.class)) return Long.valueOf(value);

        return value;
    }

    public static List<ContainerEntity> convert(List<Object> objects) throws ObjectConvertException {
        if (CollectionHelper.isEmpty(objects)) return new ArrayList<>();
        List<ContainerEntity> result = new ArrayList<>();
        try {
            for (Object row : objects) {
                result.add(convert(row));
            }
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }

        return result;
    }

    public static ContainerEntity convert(Object object) throws ObjectConvertException  {
        ContainerEntity containerEntity;
        Object[] columns = (Object[])object;
        Long id = ((BigInteger)columns[0]).longValue();
        String designation = (String)columns[1];
        String global_code = (String)columns[2];
        String local_code = (String)columns[3];
        String nominal_volume = (String)columns[4];
        String sap_code = (String)columns[5];
        Integer status_id = (Integer)columns[6];
        Long business_unit_id = columns[7] == null ? null : ((BigInteger)columns[7]).longValue();
        Long location_id = columns[8] == null ? null : ((BigInteger)columns[8]).longValue();
        Long company_id = columns[9] == null ? null : ((BigInteger)columns[9]).longValue();
        Long packaging_form_id = columns[10] == null ? null : ((BigInteger)columns[10]).longValue();

        ContainerType containerType = ContainerType.valueOf(packaging_form_id);
        containerEntity = createInstanceByType(containerType);
        containerEntity.setId(id);
        containerEntity.setDesignation(designation);
        containerEntity.setGlobalCode(global_code);
        containerEntity.setSapCode(sap_code);
        containerEntity.setLocalCode(local_code);
        containerEntity.setNominalVolume(nominal_volume);
        containerEntity.setStatus(status_id == null ? ContainerStatus.ACTIVE : ContainerStatus.valueOf(status_id.intValue()));

        if (business_unit_id != null)
            containerEntity.setBusinessUnit(new BusinessUnitEntity().setId(business_unit_id));

        if (location_id != null)
            containerEntity.setLocation(new LocationEntity().setId(location_id));

        if (company_id != null)
            containerEntity.setCompany(new CompanyEntity().setId(company_id));

        if (packaging_form_id != null)
            containerEntity.setPackagingFormEntity(new PackagingFormEntity().setId(packaging_form_id));

        return containerEntity;
    }

    private static ContainerEntity createInstanceByType(ContainerType containerType) {
        switch (containerType) {
            case Cylindrical_Clamping_Ring :
                return new CylindricalClampingRingEntity();

            case Conical_Clamping_Ring :
                return new CylindricalClampingRingEntity();

            case Conical_Cans:
                return new ConicalCansEntity();

            case Canister_Cubic:
                return new ConicalCanisterEntity();

            case IBC_Cubic:
                return new CubicEntity();

            case IBC_Cylindrical:
                return new CylindricalEntity();

            case Big_Bags:
                return new BigBagsEntity();

            case IBC_Bulk:
                return new BulkEntity();
        }

        return null;
    }
}
