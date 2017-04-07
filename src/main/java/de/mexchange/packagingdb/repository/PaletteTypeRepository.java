package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.PaletteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaletteTypeRepository extends JpaRepository<PaletteTypeEntity, Long> {

    @Query("SELECT DISTINCT c FROM PaletteTypeEntity c JOIN c.infoList info WHERE LOWER(info.type) LIKE LOWER(CONCAT('%',:type, '%'))")
    List<PaletteTypeEntity> typeahead(@Param("type") String type);

    public List<Long> deleteByIdIn(List<Long> ids);
}
