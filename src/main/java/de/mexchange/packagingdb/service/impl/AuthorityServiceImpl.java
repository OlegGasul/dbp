package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.entity.Authority;
import de.mexchange.packagingdb.repository.AuthorityRepository;
import de.mexchange.packagingdb.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
    public Authority create(Authority authority) {
        return authorityRepository.saveAndFlush(authority);
    }

    @Override
    public Long count() {
        return authorityRepository.count();
    }
}
