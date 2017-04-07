package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.HandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandleRepository extends JpaRepository<HandleEntity, Long> {
    @Query("SELECT DISTINCT c FROM HandleEntity c JOIN c.infoList info WHERE LOWER(info.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<HandleEntity> typeahead(@Param("name") String name);

    List<Long> deleteByIdIn(List<Long> ids);
}
