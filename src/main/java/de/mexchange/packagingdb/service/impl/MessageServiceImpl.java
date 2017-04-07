package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    ///////////////////////////////////////////////

    // TODO Correct
    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    // TODO Correct
    @Override
    public String getMessage(String key, Object args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(key, new Object[]{args}, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    // TODO Correct
    @Override
    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

}
