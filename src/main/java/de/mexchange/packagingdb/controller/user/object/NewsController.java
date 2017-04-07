package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.News;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Arpine on 6/14/2016.
 */

@Controller
@RequestMapping("/news")
public class NewsController extends BaseObjectController<News>{

    private Validator validator;

    @Autowired
    public NewsController(NewsService newsService, MessageService messageService, LanguageService languageService) {
        super(newsService, messageService, languageService, News.class);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(News news) {
        return super.addView(news);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final News news,
                      @RequestParam(value = "file", required = false) MultipartFile newsFile,
                      @RequestParam(value = "link", required = false) MultipartFile newsLink,
                      BindingResult result,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {
        validator.validate(news, result);
        if (!result.hasErrors() && !newsFile.isEmpty()) {
            try {

                news.setNewsFile(newsFile.getBytes());
                news.setNewsFilename(newsFile.getOriginalFilename());
                news.setNewsFileContentType(newsFile.getContentType());

            } catch (IOException e) {
                result.addError(new FieldError("news", "newsFilename", "Failed to attach file"));
            }
        }

        if (!result.hasErrors() && !newsLink.isEmpty()) {
            try {

                news.setNewsLink(newsLink.getBytes());
                news.setNewsLinkFilename(newsLink.getOriginalFilename());
                news.setNewsLinkFileContentType(newsLink.getContentType());

            } catch (IOException e) {
                result.addError(new FieldError("news", "newsLink", "Failed to attach file"));
            }
        }
        return super.add(news, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws InternalErrorException {
        return super.list(model);
    }

    @RequestMapping(value = "/newsFile/{id}", method = RequestMethod.GET)
    public void downloadNewsFile(@PathVariable("id") Long id,
                                 HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        News news = modelService.getByID(id);
        response.setContentType(news.getNewsFileContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + news.getNewsFilename());
        response.getOutputStream().write(news.getNewsFile());
        response.flushBuffer();
    }

    @RequestMapping(value = "/newsLink/{id}", method = RequestMethod.GET)
    public void downloadNewsLink(@PathVariable("id") Long id,
                                 HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        News news = modelService.getByID(id);
        response.setContentType(news.getNewsLinkFileContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + news.getNewsLinkFilename());
        response.getOutputStream().write(news.getNewsLink());
        response.flushBuffer();
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id,
                       Model model) throws InternalErrorException, DataNotFoundException {

        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(final News news,
                       @RequestParam(value = "newsFileRemoved", required = false) boolean newsFileRemoved,
                       @RequestParam(value = "file", required = false) MultipartFile newsFile,
                       @RequestParam(value = "newsLinkRemoved", required = false) boolean newsLinkRemoved,
                       @RequestParam(value = "link", required = false) MultipartFile newsLink,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(news, result);
        if (newsFileRemoved) {
            news.setNewsFilename(null);
        } else if (!result.hasErrors() && !newsFile.isEmpty()) {
            try {
                news.setNewsFile(newsFile.getBytes());
                news.setNewsFilename(newsFile.getOriginalFilename());
                news.setNewsFileContentType(newsFile.getContentType());
            } catch (IOException e) {
                result.addError(new FieldError("news", "newsFilename", "Failed to attach file"));
            }
        }

        if (newsLinkRemoved) {
            news.setNewsLinkFilename(null);
        } else if (!result.hasErrors() && !newsLink.isEmpty()) {
            try {
                news.setNewsLink(newsLink.getBytes());
                news.setNewsLinkFilename(newsLink.getOriginalFilename());
                news.setNewsLinkFileContentType(newsLink.getContentType());
            } catch (IOException e) {
                result.addError(new FieldError("news", "newsLinkFilename", "Failed to attach file"));
            }
        }
        return super.edit(news, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public @ResponseBody
    AjaxResponse listItems(@RequestParam(value = "page", required = false) int page,
                           @RequestParam(value = "count", required = false, defaultValue = "10") int count) {
        return super.listItems(page, count);
    }
}
