package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ClosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosureRepository extends JpaRepository<ClosureEntity, Long> {

    @Query("SELECT DISTINCT c FROM ClosureEntity c JOIN c.infoList info WHERE LOWER(info.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<ClosureEntity> typeahead(@Param("name") String name);

    public List<Long> deleteByIdIn(List<Long> ids);
}
