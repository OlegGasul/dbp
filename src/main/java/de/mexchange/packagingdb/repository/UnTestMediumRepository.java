package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.UnTestMediumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnTestMediumRepository extends JpaRepository<UnTestMediumEntity, Long> {

    @Query("SELECT DISTINCT c FROM UnTestMediumEntity c JOIN c.infoList info WHERE LOWER(info.medium) LIKE LOWER(CONCAT('%',:medium, '%'))")
    List<UnTestMediumEntity> typeahead(@Param("medium") String medium);

    List<Long> deleteByIdIn(List<Long> ids);
}
