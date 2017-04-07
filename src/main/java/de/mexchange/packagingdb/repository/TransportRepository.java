package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.UnTransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<UnTransportEntity, Long> {

    @Query("SELECT DISTINCT t FROM UnTransportEntity t WHERE LOWER(t.verpGrp) LIKE LOWER(CONCAT('%',:verpGrp, '%'))")
    List<UnTransportEntity> typeahead(@Param("verpGrp") String verpGrp);

    List<Long> deleteByIdIn(List<Long> ids);
}
