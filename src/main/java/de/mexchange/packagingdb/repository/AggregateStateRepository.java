package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.AggregateStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregateStateRepository extends JpaRepository<AggregateStateEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
