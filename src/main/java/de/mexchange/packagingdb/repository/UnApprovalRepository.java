package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.UnApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnApprovalRepository extends JpaRepository<UnApprovalEntity, Long> {
    List<Long> deleteByIdIn(List<Long> ids);
}
