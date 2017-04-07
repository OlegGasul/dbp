package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.BusinessUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnitEntity, Long> {

    @Query("SELECT DISTINCT bu FROM BusinessUnitEntity bu JOIN bu.infoList info WHERE LOWER(info.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<BusinessUnitEntity> typeahead(@Param("name") String name);

    List<Long> deleteByIdIn(List<Long> ids);
}
