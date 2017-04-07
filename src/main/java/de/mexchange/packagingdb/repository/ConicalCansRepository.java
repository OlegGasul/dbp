package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ConicalCansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConicalCansRepository extends JpaRepository<ConicalCansEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
