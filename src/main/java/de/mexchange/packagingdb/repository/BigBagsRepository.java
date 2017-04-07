package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.BigBagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigBagsRepository extends JpaRepository<BigBagsEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
