package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CompanyCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyCategoryRepository extends JpaRepository<CompanyCategoryEntity, Long> {


    @Query("SELECT DISTINCT c FROM CompanyCategoryEntity c JOIN c.infoList info WHERE LOWER(info.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<CompanyCategoryEntity> typeahead(@Param("name") String name);

    List<Long> deleteByIdIn(List<Long> ids);
}
