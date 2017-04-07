package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.GasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasketRepository extends JpaRepository<GasketEntity, Long> {

    @Query("SELECT DISTINCT c FROM GasketEntity c JOIN c.infoList info WHERE LOWER(info.description) LIKE LOWER(CONCAT('%',:desc, '%'))")
    List<GasketEntity> typeahead(@Param("desc") String desc);

    List<Long> deleteByIdIn(List<Long> ids);
}
