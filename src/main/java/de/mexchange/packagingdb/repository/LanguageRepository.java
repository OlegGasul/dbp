package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Garik
 * Date: 5/15/16
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
}
