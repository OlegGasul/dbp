package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.CubicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CubicRepository extends JpaRepository<CubicEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
