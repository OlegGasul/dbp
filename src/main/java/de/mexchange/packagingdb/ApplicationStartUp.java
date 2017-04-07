package de.mexchange.packagingdb;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.entity.Authority;
import de.mexchange.packagingdb.service.AuthorityService;
import de.mexchange.packagingdb.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private LanguageService languageService;

    private static Logger LOG = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createDefaultData();
    }

    protected void createDefaultData() {
        createRoles();
        createLanguages();
    }

    protected void createRoles() {
        Long countOfAuthorities = authorityService.count();

        if (countOfAuthorities > 0) {
            LOG.info("Authorities Default Data exists");
        } else {
            // Default Data Installer
            Authority authority;
            for (UserRole authorities : UserRole.values()) {
                authority = new Authority(authorities);
                authority = authorityService.create(authority);
                LOG.info(String.format("Inserted Default Data [Authority: %s]", authority.getName()));
            }
        }
    }

    private void createLanguages() {
        Long countOfLanguages = languageService.count();
        if (countOfLanguages > 0) {
            LOG.info("Language Default Data exists");
            //update if status has been changed
            for (Language lang : Language.values()) {
                languageService.update(lang);
            }
        } else {
            for (Language lang : Language.values()) {
                 languageService.create(lang);
            }
        }
    }
}
