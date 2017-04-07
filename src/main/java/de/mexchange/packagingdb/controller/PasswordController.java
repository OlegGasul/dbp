package de.mexchange.packagingdb.controller;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.UserService;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;
import de.mexchange.packagingdb.service.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Artur on 6/11/2016.
 */
@Controller
public class PasswordController {

    private static final String RESET_PASSWORD = "reset-password";

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private MessageService messageService;

    @Autowired
    public PasswordController(UserService userService, PasswordEncoder passwordEncoder, MessageService messageService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
    }

    @RequestMapping(value = PasswordUtil.MAPPING_RESET_PASSWORD, method = RequestMethod.GET)
    public String resetPassword(String email, String key,
                                final RedirectAttributes redirectAttributes,
                                HttpSession httpSession) {

        if(StringHelper.isBlank(email) || StringHelper.isBlank(key)){
            redirectAttributes.addAttribute("messageDanger",messageService.getMessage("msg.reset.user.password.error.internal"));
            return "redirect:login";
        }

        try {

            User user = userService.getByEmailAndPassword(email, key);
            if (user.getStatus().getValue() == UserStatus.PENDING.getValue()) {
                httpSession.setAttribute(RESET_PASSWORD, user);
                return "redirect:reset-user-password";
            }

        } catch (DataNotFoundException e) {
            redirectAttributes.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.not.found"));
        } catch (InternalErrorException e) {
            redirectAttributes.addAttribute("messageDanger",messageService.getMessage("msg.reset.user.password.error.internal"));
        }

        return "redirect:login";
    }

    @RequestMapping(value = PasswordUtil.MAPPING_FORGET_PASSWORD, method = RequestMethod.GET)
    public String forgetUserPassword(String email, String key, HttpSession httpSession, Model model) {

        if(StringHelper.isBlank(email) || StringHelper.isBlank(key)){
            model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.internal"));
            return "forget-password";
        }

        try {
            User user = userService.getByEmail(email);
            String password = PasswordUtil.toToken(user.getPassword());
            if (password.equals(key)) {
                httpSession.setAttribute(RESET_PASSWORD, user);
                return "redirect:reset-user-password";
            } else {
                model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.invalid.key"));
            }
        } catch (DataNotFoundException e) {
            model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.not.found"));
        } catch (InternalErrorException e) {
            model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.internal"));
        }

        return "forget-password";
    }

    @RequestMapping(value = "/reset-user-password", method = RequestMethod.GET)
    public String resetUserPassword(HttpSession httpSession, Model model,  final RedirectAttributes redirectAttributes) {
        User user = (User) httpSession.getAttribute(RESET_PASSWORD);
        if (user == null) {
            redirectAttributes.addAttribute("messageDanger",messageService.getMessage("msg.reset.user.password.error.internal"));
            return "redirect:login";
        }
        model.addAttribute("user", user);
        return "/reset-password";
    }

    @RequestMapping(value = "/reset-user-password", method = RequestMethod.POST)
    public String resetUserPassword(final String password,
                                    Model model,
                                    final RedirectAttributes redirectAttributes,
                                    HttpSession httpSession
                                    ) {

        if(StringHelper.isBlank(password)){
            model.addAttribute("messageDanger",messageService.getMessage("err.field.user.psw.required"));
            User user = (User) httpSession.getAttribute(RESET_PASSWORD);
            model.addAttribute("user", user);
            return "/reset-password";
        }

        User user = (User) httpSession.getAttribute(RESET_PASSWORD);
        httpSession.removeAttribute(RESET_PASSWORD);
        if (user == null) {
            model.addAttribute("messageDanger",messageService.getMessage("msg.reset.user.password.error"));
            return "/reset-password";
        }

        User resetUser = new User(user);
        resetUser.setPassword(passwordEncoder.encode(password));
        int status = user.getStatus().getValue();
        try {
            if (status == UserStatus.PENDING.getValue() || status == UserStatus.ACTIVE.getValue()) {
                resetUser.setStatus(UserStatus.ACTIVE);
                userService.resetPassword(resetUser);
                redirectAttributes.addFlashAttribute("messageSuccess", messageService.getMessage("msg.reset.user.password.success"));
                return "redirect:login";
            }
        } catch (DataNotFoundException e) {
        } catch (InternalErrorException e) {
        }
        model.addAttribute("messageDanger",messageService.getMessage("msg.reset.user.password.error.internal"));
        return "/reset-password";
    }

    @RequestMapping(value = "/forget-password", method = RequestMethod.GET)
    public String forgetPassword() {
        return "/forget-password";
    }

    @RequestMapping(value = "/forget-user-password", method = RequestMethod.POST)
    public String forgetPasswordSend(HttpServletRequest httpRequest, Model model, final RedirectAttributes redirectAttributes, String email) {

        if(StringHelper.isBlank(email)){
            model.addAttribute("messageDanger",messageService.getMessage("err.field.user.email.required"));
            return "forget-password";
        }

        try {
            String url = PasswordUtil.preparePasswordChangeUrl(httpRequest, EmailNotificationEvent.FORGET_PASSWORD);
            userService.forgetPassword(email, url);
            redirectAttributes.addFlashAttribute("messageSuccess", messageService.getMessage("msg.forget.psw.sent.success", email));
            return "redirect:login";
        } catch (InternalErrorException e) {
            model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.internal"));
        } catch (DataNotFoundException e) {
            model.addAttribute("messageDanger",messageService.getMessage("msg.forget.psw.sent.error.not.found", email));
        }
        return "forget-password";
    }
}
