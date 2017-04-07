package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("SELECT DISTINCT c FROM CompanyEntity c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:form, '%'))")
    List<CompanyEntity> typeahead(@Param("form") String form);

    List<Long> deleteByIdIn(List<Long> ids);
}
