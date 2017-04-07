package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.domain.lcp.ProcessType;
import de.mexchange.packagingdb.entity.ProcessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {

    @Query("SELECT DISTINCT c FROM ProcessEntity c JOIN c.infoList info WHERE LOWER(info.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<ProcessEntity> typeahead(@Param("name") String name);

    List<Long> deleteByIdIn(List<Long> ids);

    Page<ProcessEntity> findByProcessType(Pageable pageable, ProcessType type);

    Long countByProcessType(ProcessType type);
}
