package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.LanguageEntity;
import de.mexchange.packagingdb.repository.LanguageRepository;
import de.mexchange.packagingdb.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Garik
 * Date: 5/15/16
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(readOnly = true)
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public Language create(Language language) {
        LanguageEntity entity = new LanguageEntity(language);
        repository.saveAndFlush(entity);
        return language;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public Language update(Language language) {
        LanguageEntity entity = repository.findOne(language.getValue());
        if (entity != null ) {
            if (entity.getStatus() != language.getStatus()) {
                entity.setStatus(language.getStatus());
                repository.saveAndFlush(entity);
                return language;
            }
        }
        return null;
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
