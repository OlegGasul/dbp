package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository  extends JpaRepository<Authority, Integer> {
}
