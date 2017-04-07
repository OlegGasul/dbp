package de.mexchange.packagingdb.controller.user.object.container;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.search.FieldInfo;
import de.mexchange.packagingdb.common.search.ValueOption;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.controller.response.ListAjaxResponse;
import de.mexchange.packagingdb.domain.CommonContainer;
import de.mexchange.packagingdb.service.BulkService;
import de.mexchange.packagingdb.service.ContainerService;
import de.mexchange.packagingdb.service.util.FieldsHelper;

/**
 * User: Garik
 * Date: 6/23/16
 * Time: 2:12 AM
 */
@Controller
@RequestMapping("/container")
public class SearchController {

    @Autowired
    private BulkService bulkService;

    @Autowired
    private ContainerService containerService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForm(Model model) throws InternalErrorException {
        List<FieldInfo> fields = new ArrayList<>();
        FieldsHelper.getContainerFields(fields);
        model.addAttribute("fields", fields);
        model.addAttribute("options", ValueOption.values());
        model.addAttribute("dataCount", containerService.getListCount(null, null, null));

        return "container/search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    AjaxResponse search( @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "count", required = false, defaultValue = "10") int count,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldOperator") String fieldOperator,
            @RequestParam("fieldValue") String fieldValue) {

        FieldInfo foundField = findField(fieldName);
        try {
            ValueOption option = ValueOption.valueOf(fieldOperator);

            long listCount = containerService.getListCount(foundField, option, fieldValue);
            if (page * 10L > listCount) {
                page = (int) (listCount / 10);
                if (listCount % 10 > 0) {
                    //todo
                    //page += 1;
                }
            }

            List<CommonContainer> containers = containerService.search(page, count, foundField, option, fieldValue);

            return new ListAjaxResponse("success", containers, listCount);
        } catch (Exception e) {
            return new AjaxResponse("error", "Internal Error");
        }
    }

    private FieldInfo findField(String fieldName){
        List<FieldInfo> fields = new ArrayList<>();
        FieldsHelper.getContainerFields(fields);
        for(FieldInfo fieldInfo : fields){
            if(fieldInfo.getName().equalsIgnoreCase(fieldName)){
                return fieldInfo;
            }
        }
        return null;
    }
}
