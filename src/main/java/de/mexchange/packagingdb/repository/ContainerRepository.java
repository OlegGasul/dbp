package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.ContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<ContainerEntity, Long>,JpaSpecificationExecutor,
                                                                                    ContainerCustomRepository
{

}
