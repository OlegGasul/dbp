package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.UnPackagingInstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagingInstructionRepository extends JpaRepository<UnPackagingInstructionEntity, Long> {

    @Query("SELECT DISTINCT v FROM UnPackagingInstructionEntity v WHERE LOWER(v.transportLaw) LIKE LOWER(CONCAT('%',:transportLaw, '%'))")
    List<UnPackagingInstructionEntity> typeahead(@Param("transportLaw") String transportLaw);

    List<Long> deleteByIdIn(List<Long> ids);
}
