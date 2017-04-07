package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.UnAssimilationListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssimilationListRepository extends JpaRepository<UnAssimilationListEntity, Long> {
    @Query("SELECT DISTINCT a FROM UnAssimilationListEntity a WHERE LOWER(a.designation) LIKE LOWER(CONCAT('%',:designation, '%'))")
    List<UnAssimilationListEntity> typeahead(@Param("designation") String designation);
    List<Long> deleteByIdIn(List<Long> ids);
}
