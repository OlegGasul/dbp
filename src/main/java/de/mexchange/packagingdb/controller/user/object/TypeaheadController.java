package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.constant.TypeaheadEnum;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.NoServiceFoundException;
import de.mexchange.packagingdb.domain.AbstractModel;
import de.mexchange.packagingdb.service.ModelService;
import de.mexchange.packagingdb.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Garik on 5/11/16.
 */
@RestController
@RequestMapping("/objects")
public class TypeaheadController {

	private ServiceFactory serviceFactory;

	@Autowired
	public TypeaheadController(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@RequestMapping(value = "/typeahead/{type}", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<? extends AbstractModel>> get(@PathVariable("type") int type,
														     @RequestParam("info") String info) {

		TypeaheadEnum typeahead = TypeaheadEnum.valueOf(type);
		if (typeahead != null) {
			try {
				ModelService service = serviceFactory.get(typeahead.getClazz());
				List result = service.typeahead(info);
				return new ResponseEntity(result, HttpStatus.OK);
			} catch (NoServiceFoundException e) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			} catch (InternalErrorException e) {
				return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}
