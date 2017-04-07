package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ConicalClampingRingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConicalClampingRingRepository extends JpaRepository<ConicalClampingRingEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
