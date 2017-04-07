package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ContainerPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerPhotoRepository extends JpaRepository<ContainerPhotoEntity, Long> {

    List<Long> deleteByIdIn(List<Long> ids);

    @Query("SELECT ph FROM ContainerPhotoEntity ph WHERE ph.id = :id")
    ContainerPhotoEntity getPhotoByID(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM ContainerPhotoEntity ph WHERE ph.id = :id")
    Integer deletePhotoByID(@Param("id") Long id);
}
