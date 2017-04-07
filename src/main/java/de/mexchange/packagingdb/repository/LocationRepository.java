package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository  extends JpaRepository<LocationEntity, Long> {

    @Query("SELECT DISTINCT c FROM LocationEntity c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    List<LocationEntity> typeahead(@Param("name") String name);

    List<Long> deleteByIdIn(List<Long> ids);
}
