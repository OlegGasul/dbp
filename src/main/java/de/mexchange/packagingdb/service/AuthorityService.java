package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.entity.Authority;

/**
 * Created by Arthur on 5/14/2016.
 */
public interface AuthorityService {

    Authority create(Authority authority);

    Long count();
}
