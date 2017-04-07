package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.PackagingFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagingFormRepository extends JpaRepository<PackagingFormEntity, Long> {

    @Query("SELECT DISTINCT c FROM PackagingFormEntity c JOIN c.infoList info WHERE LOWER(info.form) LIKE LOWER(CONCAT('%',:form, '%'))")
    List<PackagingFormEntity> typeahead(@Param("form") String form);

    List<Long> deleteByIdIn(List<Long> ids);
}
