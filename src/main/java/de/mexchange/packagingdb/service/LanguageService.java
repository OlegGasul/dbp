package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.domain.lcp.Language;

/**
 * Created with IntelliJ IDEA.
 * User: Garik
 * Date: 5/15/16
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LanguageService {

    Language create(Language language);

    Language update(Language language);

    Long count();
}
