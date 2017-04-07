package de.mexchange.packagingdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Artur on 7/1/2016.
 */
@Controller
public class LocaleController {

    @RequestMapping(value = "/locale", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(required = false) String lang,
                               @RequestHeader(value = "referer", required = false, defaultValue = "/") final String referer) {
        return "redirect:" + referer;
    }

}
