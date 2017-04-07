package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CylindricalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CylindricalRepository extends JpaRepository<CylindricalEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
