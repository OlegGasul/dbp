package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Garik on 4/30/16.
 */
public interface UserService extends UserDetailsService {

	User add(User user) throws InternalErrorException;

	User add(User user, String url) throws InternalErrorException;

	User getByID(Long id) throws DataNotFoundException, InternalErrorException;

	User getByEmail(String email) throws DataNotFoundException, InternalErrorException;

	User getByEmailAndPassword(String email, String psw) throws DataNotFoundException, InternalErrorException;

	List<User> getUsersByRole(UserRole role) throws InternalErrorException;

	long getListCount() throws InternalErrorException;

	long getListCount(String[] roles, String[] statuses, String search) throws InternalErrorException;

	public List<User> getList(int page, int count) throws InternalErrorException;

	public List<User> getList(int page, int count, String[] roles, String[] statuses, String search) throws InternalErrorException;

	User edit(User user) throws DataNotFoundException, InternalErrorException;

	User resetPassword(User user) throws DataNotFoundException, InternalErrorException;

	User forgetPassword(String email, String url) throws DataNotFoundException, InternalErrorException;


	void delete(User user) throws DataNotFoundException, InternalErrorException;

	List<User> typeahead(String info) throws InternalErrorException;

	void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException;
}
