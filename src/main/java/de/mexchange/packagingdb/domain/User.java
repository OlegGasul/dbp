package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Garik on 4/30/16.
 */
public class User extends AbstractModel {


	@Email(message = "{err.field.user.email.invalid}")
	@NotEmpty(message = "{err.field.user.email.required}")
	private String email;

	private String password;

	@NotNull(message = "{err.field.user.status.required}")
	private UserStatus status;

	@NotNull(message = "{err.field.user.role.required}")
	private UserRole role;

	@NotEmpty(message = "{err.field.user.name.required}")
	private String name;

	@NotEmpty(message = "{err.field.user.surname.required}")
	private String surname;

	private String phoneNumber;

	private String faxNumber;

	private String mobileNumber;

	private Address address;

	private Collection<? extends GrantedAuthority> authorities;

	public User() {}

	public User(User user) {
		id = user.getId();
		email = user.getEmail();
		password = user.getPassword();
		status = user.getStatus();
		role = user.getRole();
		authorities = user.getAuthorities();
		name = user.getName();
		surname = user.getSurname();
		phoneNumber = user.getPhoneNumber();
		faxNumber = user.getFaxNumber();
		mobileNumber = user.getMobileNumber();
		address = user.getAddress();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Collection<? extends GrantedAuthority>  getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
			"email='" + email + '\'' +
			", status=" + status +
			", role=" + role +
			", name='" + name + '\'' +
			", surname='" + surname + '\'' +
			", phoneNumber='" + phoneNumber + '\'' +
			", faxNumber='" + faxNumber + '\'' +
			", mobileNumber='" + mobileNumber + '\'' +
			", address='" + address + '\'' +
			'}';
	}
}
