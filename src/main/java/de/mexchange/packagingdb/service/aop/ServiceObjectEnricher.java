package de.mexchange.packagingdb.service.aop;

import de.mexchange.packagingdb.domain.AbstractModel;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.service.util.ServiceHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Garik on 5/8/16.
 */
@Aspect
@Component
public class ServiceObjectEnricher {

	/**
	 * Pointcut of add methods
	 */
	@Pointcut("execution(public * *..*.service.*.add(..))")
	public void methodsToAddObjects() {}

	/**
	 * Adding createdBy object on models
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("methodsToAddObjects()")
	public Object addObject(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		if (args != null) {
			User authenticatedUser = ServiceHelper.getAuthenticatedUser();
			for (Object item : args) {
				if (item instanceof AbstractModel) {
					AbstractModel model = (AbstractModel) item;
					model.setCreatedBy(authenticatedUser);
				}
			}
		}
		return point.proceed();
	}

	/**
	 * Pointcut of edit methods
	 */
	@Pointcut("execution(public * *..*.service.*.edit(..))")
	public void methodsToEditObjects() {}

	/**
	 * Changing modifiedBy object on model
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("methodsToEditObjects()")
	public Object editObject(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		if (args != null) {
			User authenticatedUser = ServiceHelper.getAuthenticatedUser();
			for (Object item : args) {
				if (item instanceof AbstractModel) {
					AbstractModel model = (AbstractModel) item;
					model.setModifiedBy(authenticatedUser);
				}
			}
		}
		return point.proceed();
	}
}

