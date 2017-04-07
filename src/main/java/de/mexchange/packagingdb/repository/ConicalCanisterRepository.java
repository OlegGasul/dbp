package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ConicalCanisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConicalCanisterRepository extends JpaRepository<ConicalCanisterEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);
}
