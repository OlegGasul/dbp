package de.mexchange.packagingdb.service.impl;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.NotificationException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.common.util.transformer.DataTransformer;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.entity.AddressEntity;
import de.mexchange.packagingdb.entity.Authority;
import de.mexchange.packagingdb.entity.UserEntity;
import de.mexchange.packagingdb.repository.AuthorityRepository;
import de.mexchange.packagingdb.repository.UserRepository;
import de.mexchange.packagingdb.repository.specification.UserSpecification;
import de.mexchange.packagingdb.security.SessionUser;
import de.mexchange.packagingdb.service.AbstractService;
import de.mexchange.packagingdb.service.UserService;
import de.mexchange.packagingdb.service.notification.EmailNotificationService;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;
import de.mexchange.packagingdb.service.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Garik on 4/30/16.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractService<User, UserEntity> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	public EmailNotificationService emailService;

	/**
	 * retrieve user's data to complete authentication
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("err.msg.invalid.credential");
		}
		User user = fromEntity(userEntity);
		return new SessionUser(user);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public User add(User user) throws InternalErrorException {

		UserEntity userEntity = toEntity(user);
		try {
			userEntity = userRepository.save(userEntity);
			user = fromEntity(userEntity);
			return user;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public User add(User user, String url) throws InternalErrorException {

		String psw = UUID.randomUUID().toString();
		user.setPassword(psw);

		url = String.format(url, user.getEmail(), psw);

		UserEntity userEntity = toEntity(user);
		try {
			userEntity = userRepository.save(userEntity);
			User u = fromEntity(userEntity);
			if(user.getStatus().getValue() == UserStatus.PENDING.getValue()){
				emailService.sendEmail(u, url, EmailNotificationEvent.ACTIVATE_ACCOUNT);
			}

			return u;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}

	@Override
	public User getByID(Long id) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity;
		try {
			userEntity = userRepository.findOne(id);
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
		if (userEntity == null) {
			throw new DataNotFoundException();
		}
		return fromEntity(userEntity);
	}

	@Override
	public User getByEmail(String email) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("err.msg.invalid.credential");
		}
		return fromEntity(userEntity);
	}

	@Override
	public User getByEmailAndPassword(String email, String psw) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity;
		try {
			userEntity = userRepository.findByEmailAndKey(email, psw);
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
		if (userEntity == null) {
			throw new DataNotFoundException();
		}
		return fromEntity(userEntity);
	}

	@Override
	public List<User> getUsersByRole(UserRole role) throws InternalErrorException {
		try {
			//Authority authority = authorityRepository.getOne(role.getValue());
			Authority authority = new Authority(role);
			List<UserEntity> users = userRepository.findByRole(authority);
			return fromEntities(users);
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}

    @Override
    public long getListCount() throws InternalErrorException {
        try {
            return userRepository.count();
        } catch (Exception e) {
            throw new InternalErrorException("Failed to retrieve count of list items", e);
        }
    }

	@Override
	public long getListCount(String[] roles, String[] statuses, String search) throws InternalErrorException {
		try {

			Specifications<UserEntity> spec = null;
			if(!CollectionHelper.isEmpty(Arrays.asList(statuses))){
				spec = Specifications.where( UserSpecification.hasStatuses(statuses));
			}

			if(!CollectionHelper.isEmpty(Arrays.asList(roles))){
				if(spec == null){
					spec = Specifications.where( UserSpecification.hasRoles(roles));
				} else {
					spec = spec.and(UserSpecification.hasRoles(roles));
				}
			}

			if(StringHelper.isNotBlank(search)){
				if(spec == null){
					spec = Specifications.where( UserSpecification.searchBy(search));
				} else {
					spec = spec.and(UserSpecification.searchBy(search));
				}
			}

			if(spec != null){
				return userRepository.count(spec);
			} else {
				return userRepository.count();
			}
		} catch (Exception e) {
			throw new InternalErrorException("Failed to retrieve count of list items", e);
		}
	}

	@Override
	public List<User> getList(int page, int count) throws InternalErrorException {
		Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
		try {

			Page<UserEntity> data = userRepository.findAll(pageable);
			List<UserEntity> listEntities = data.getContent();
			return fromEntities(listEntities);
		}  catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}

	@Override
	public List<User> getList(int page, int count, String[] roles, String[] statuses, String search) throws InternalErrorException {


		Pageable pageable = new PageRequest(page, count, Sort.Direction.ASC, "id");
		try {
			Specifications<UserEntity> spec = null;
			if(!CollectionHelper.isEmpty(Arrays.asList(statuses))){
				spec = Specifications.where( UserSpecification.hasStatuses(statuses));
			}

			if(!CollectionHelper.isEmpty(Arrays.asList(roles))){
				if(spec == null){
					spec = Specifications.where( UserSpecification.hasRoles(roles));
				} else {
					spec = spec.and(UserSpecification.hasRoles(roles));
				}
			}

			if(StringHelper.isNotBlank(search)){
				if(spec == null){
					spec = Specifications.where( UserSpecification.searchBy(search));
				} else {
					spec = spec.and(UserSpecification.searchBy(search));
				}
			}

			Page<UserEntity> data;
			if(spec != null){
				data  = userRepository.findAll(spec, pageable);
			} else {
				data  = userRepository.findAll(pageable);
			}

			List<UserEntity> listEntities = data.getContent();
			return fromEntities(listEntities);
		}  catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public User edit(User user) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findOne(user.getId());
			if (userEntity == null) {
				throw new DataNotFoundException();
			}

			UserEntity forMerge = toEntity(user);
			AddressEntity address = userEntity.getAddress();
			if (address == null) {
				address = new AddressEntity();
			}

			AddressEntity message = DataTransformer.transform(user.getAddress());
			if (message != null) {
				address.setCountry(message.getCountry());
				address.setCity(message.getCity());
				address.setStreet(message.getStreet());
				address.setZipCode(message.getZipCode());
				forMerge.setAddress(address);
			} else {
				forMerge.setAddress(null);
			}

			BeanUtils.copyProperties(forMerge, userEntity);
			userEntity = userRepository.saveAndFlush(userEntity);
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}

		return fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public User resetPassword(User user) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.getOne(user.getId());
			if (userEntity == null) {
				throw new DataNotFoundException();
			}
			userEntity.setPasswordHash(user.getPassword());
			userEntity.setModifiedBy(userEntity);
			userEntity.setStatus(user.getStatus());
			userEntity = userRepository.saveAndFlush(userEntity);

		} catch (Exception e) {
			throw new InternalErrorException(e);
		}

		return fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = InternalErrorException.class)
	public User forgetPassword(String email, String url) throws DataNotFoundException, InternalErrorException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new DataNotFoundException();
		}

		User u = fromEntity(userEntity);
		String password = PasswordUtil.toToken(u.getPassword());
		url = String.format(url, u.getEmail(), password);

		try {
			emailService.sendEmail(u, url, EmailNotificationEvent.FORGET_PASSWORD);
		} catch (NotificationException e) {
			throw new InternalErrorException(e);
		}
		return u;
	}

	@Override
	public void delete(User user) throws DataNotFoundException, InternalErrorException {
	}

	@Override
	public List<User> typeahead(String info) throws InternalErrorException {
		try {
			return fromEntities(userRepository.typeahead(info));
		} catch (Exception e) {
			throw new InternalErrorException("Failed to retrieve User objects [name:" + info + "]", e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {DataNotFoundException.class, InternalErrorException.class})
	public void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException {
		List<Long> removedIDs;
		try {
			removedIDs = userRepository.deleteByIdIn(ids);
		} catch (Exception e) {
			throw new  InternalErrorException("Failed to remove User objects [IDs: " + ids + "]", e);
		}

		if (removedIDs.size() != ids.size()) {
			// TODO collect not valid IDs
			throw new DataNotFoundException();
		}
	}

	@Override
	public UserEntity toEntity(User dto) {
		return DataTransformer.transform(dto);
	}

	@Override
	public User fromEntity(UserEntity entity) {
		return DataTransformer.transform(entity);
	}

	@Override
	public User fromEntity(UserEntity entity, Language language) {
		return DataTransformer.transform(entity);
	}
}
