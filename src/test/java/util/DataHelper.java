package util;

import de.mexchange.packagingdb.BaseTest;
import de.mexchange.packagingdb.domain.BusinessUnit;
import de.mexchange.packagingdb.domain.BusinessUnitInfo;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.BusinessUnitEntity;
import de.mexchange.packagingdb.entity.BusinessUnitInfoEntity;
import de.mexchange.packagingdb.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Arthur on 5/28/2016.
 */
public class DataHelper {

    // region <ENTITIES>
    public static BusinessUnitEntity createBusinessUnitEntity() {
        BusinessUnitEntity entity = new BusinessUnitEntity();
        entity.setMatchCode(BaseTest.getRandomMatchCode());
        entity.setMisc(BaseTest.getRandomString(5, true));
        Set<BusinessUnitInfoEntity> infoEntities = new HashSet<>();
        BusinessUnitInfoEntity infoEntity;
        for (Language language : Language.values()) {
            infoEntity = new BusinessUnitInfoEntity();
            infoEntity.setLanguage(new LanguageEntity(language));
            infoEntity.setName(BaseTest.getRandomBUName());
            infoEntities.add(infoEntity);
        }
        entity.setInfoList(infoEntities);
        return entity;
    }
    //endregion

    // region <DTO
    public static BusinessUnit createBusinessUnit() {
        BusinessUnit dto = new BusinessUnit();
        dto.setMatchCode(BaseTest.getRandomMatchCode());
        dto.setMisc(BaseTest.getRandomString(5, true));
        List<BusinessUnitInfo> infoDtos = new ArrayList<>();
        BusinessUnitInfo infoDto;
        for (Language language : Language.values()) {
            infoDto = new BusinessUnitInfo();
            infoDto.setLanguage(language);
            infoDto.setName(BaseTest.getRandomBUName());
            infoDtos.add(infoDto);
        }
        dto.setInfoList(infoDtos);
        return dto;
    }
    // endregion
}
