package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CouplingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouplingRepository extends JpaRepository<CouplingEntity, Long> {

    @Query("SELECT DISTINCT c FROM CouplingEntity c JOIN c.infoList info WHERE LOWER(info.description) LIKE LOWER(CONCAT('%',:description, '%'))")
    List<CouplingEntity> typeahead(@Param("description") String description);

    public List<Long> deleteByIdIn(List<Long> ids);

}
