package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.News;
import de.mexchange.packagingdb.domain.lcp.Language;
import java.util.List;

/**
 * Created by Arpine on 6/14/2016.
 */
public interface NewsService extends ModelService<News> {

    List<News> getAvailableNews(Language language) throws InternalErrorException;

    News edit(News news) throws DataNotFoundException, InternalErrorException;

}
