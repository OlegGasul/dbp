package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.entity.LanguageEntity;
import de.mexchange.packagingdb.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Arpine on 6/14/2016.
 */

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    @Query("SELECT DISTINCT c FROM NewsEntity c JOIN c.infoList info WHERE LOWER(info.title) LIKE LOWER(CONCAT('%',:title, '%'))")
    List<NewsEntity> typeahead(@Param("title") String title);

    @Query("SELECT DISTINCT c FROM NewsEntity c WHERE c.newsDate <= :today AND c.expirationDate >= :today Order by c.newsDate")
    List<NewsEntity> getAvailableNews(@Param("today") Date today);

    List<Long> deleteByIdIn(List<Long> ids);


}
