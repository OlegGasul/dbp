package de.mexchange.packagingdb.service.impl;
import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.News;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.NewsEntity;
import de.mexchange.packagingdb.repository.NewsRepository;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serozh on 6/16/2016.
 */
@Service
@Transactional(readOnly = true)
public class NewsServiceImpl extends AbstractService<News, NewsEntity> implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MessageService messageService;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public News add(News news) throws InternalErrorException {
        try {
            NewsEntity entity = toEntity(news);
            entity = newsRepository.save(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(messageService.getMessage("news.add.error", news), e);
        }
    }

    @Override
    public News getByID(Long id) throws DataNotFoundException, InternalErrorException {
        NewsEntity entity;
        try {
            entity = newsRepository.findOne(id);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        if (entity == null) {
            throw new DataNotFoundException(messageService.getMessage("news.not.found", id));
        }
        return fromEntity(entity);
    }

    @Override
    public List<News> getAll(Language language) {
        return fromEntities(newsRepository.findAll(), language);
    }

    @Override
    public long getListCount() throws InternalErrorException {
        try {
            return newsRepository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

    @Override
    public List<News> getList(int page, int count) throws InternalErrorException {
        Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
        try {
            Page<NewsEntity> data = newsRepository.findAll(pageable);
            List<NewsEntity> listEntities = data.getContent();
            return fromEntities(listEntities);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<News> getAvailableNews(Language language) throws InternalErrorException {
        try {
            Date today = new Date();
            List<NewsEntity> data = newsRepository.getAvailableNews(today);
            return fromEntities(data, language);
        }  catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public News edit(News news) throws DataNotFoundException, InternalErrorException {
        NewsEntity entity = newsRepository.findOne(news.getId());

        if (entity == null) {
            throw new DataNotFoundException();
        }
        try {
            BeanUtils.copyProperties(toEntity(news), entity);
            entity = newsRepository.saveAndFlush(entity);
            return fromEntity(entity);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<News> typeahead(String info) throws InternalErrorException {
        try {
            return fromEntities(newsRepository.typeahead(info));
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to retrieve News objects", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByID(Long id) throws DataNotFoundException, InternalErrorException {
        try {
            newsRepository.delete(id);
        } catch (Exception e) {
            throw new InternalErrorException("Failed to remove News objects [ID: " + id + "]", e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
        List<Long> removedIDs;
        try {
            removedIDs = newsRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            throw new  InternalErrorException("Failed to remove News objects [IDs: " + ids + "]", e);
        }

        if (removedIDs.size() != ids.size()) {
            // TODO collect not valid IDs
            throw new DataNotFoundException();
        }
    }
    /////////////////////////////////////////////////////////////////////
    @Override
    public NewsEntity toEntity(News dto) {
        return DataTransformer.transform(dto);
    }

    @Override
    public News fromEntity(NewsEntity entity) {
        return DataTransformer.transform(entity);
    }

    @Override
    public News fromEntity(NewsEntity entity, Language language) {
        return DataTransformer.transform(entity, language);
    }

    @Override
    public List<News> fromEntities(List<NewsEntity> entities) {
        List<News> dtoList = new ArrayList<>(entities.size());

        for (NewsEntity entity : entities) {
            dtoList.add(DataTransformer.transform(entity, false));
        }

        return dtoList;
    }
}
