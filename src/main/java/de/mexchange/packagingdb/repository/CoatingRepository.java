package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CoatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoatingRepository extends JpaRepository<CoatingEntity, Long> {

    @Query("SELECT DISTINCT c FROM CoatingEntity c JOIN c.infoList info WHERE LOWER(info.description) LIKE LOWER(CONCAT('%',:desc, '%'))")
    List<CoatingEntity> typeahead(@Param("desc") String desc);

    public List<Long> deleteByIdIn(List<Long> ids);
}
