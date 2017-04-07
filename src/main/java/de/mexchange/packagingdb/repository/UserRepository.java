package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.Authority;
import de.mexchange.packagingdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,JpaSpecificationExecutor {

    List<UserEntity> findByRole(Authority authority);

    UserEntity findByEmail(String lowercaseLogin);

    @Query("SELECT DISTINCT u FROM UserEntity u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%',:email, '%')) AND LOWER(u.passwordHash) LIKE LOWER(CONCAT('%',:passwordHash, '%')) ")
    UserEntity findByEmailAndKey(@Param("email") String email, @Param("passwordHash") String passwordHash);

    @Query("SELECT DISTINCT u FROM UserEntity u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:info, '%')) OR LOWER(u.surname) LIKE LOWER(CONCAT('%',:info, '%'))")
    List<UserEntity> typeahead(@Param("info") String info);

    List<Long> deleteByIdIn(List<Long> ids);
}
