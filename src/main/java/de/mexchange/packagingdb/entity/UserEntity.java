package de.mexchange.packagingdb.entity;

import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.entity.converter.UserStatusConverter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * User entity class.
 */
@Entity
@Table(name = "user")
public class UserEntity extends AbstractEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	// unique = true removed as in old db email address is not unique.
    @NotEmpty
    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

	@Column(name = "status_id", nullable = false)
	@Convert(converter = UserStatusConverter.class)
	private UserStatus status;

	@NotNull
	@ManyToOne
	@JoinColumn(name="profile_id", foreignKey =  @ForeignKey(name="FK_user_profile"))
	private Authority role;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "fax_number")
	private String faxNumber;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "other")
	private String other;

	//Straße, Postleitzahl, Ort, Land
	//Note: address contains country ,road, place and zipCode
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", insertable = true, updatable = true, foreignKey = @ForeignKey(name = "FK_user_address"))
	private AddressEntity address;

    /**
     * Initializes a new instance of the class.
     */
    public UserEntity() { }

    // region <GET/SET>
    /**
     * Gets the entity id (PK).
     *
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the entity ID.
     *
     * @param id
     * @return updated <code>UserEntity</code> object.
     */
    public UserEntity setId(Long id) {
        if (id == null || id == 0) {
            this.id = null;
        } else {
            this.id = id;
        }
        return this;
    }

    /**
     * Sets the user's email.
     *
     * @param email
     * @return updated <code>UserEntity</code> object.
     */
    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Gets the email.
     *
     * @return {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the status.
     *
     * @return {@link #status}
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status
     * @return updated <code>UserEntity</code> object.
     */
    public UserEntity setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

	public Authority getRole() {
		return role;
	}

	public UserEntity setRole(Authority role) {
		this.role = role;
		return this;
	}

	/**
     * Spring Security Authorities
     * @return user's authorities list
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(getRole().getName());
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    /**
     * Sets the user's authentication password.
     *
     * @param passwordHash
     * @return updated <code>UserEntity</code> object.
     */
    public UserEntity setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

	public String getName() {
		return name;
	}

	/**
	 * Sets the user's name.
	 *
	 * @param name
	 * @return updated <code>UserEntity</code> object.
	 */
	public UserEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the user's surname.
	 *
	 * @param surname
	 * @return updated <code>UserEntity</code> object.
	 */
	public UserEntity setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the user's phone number.
	 *
	 * @param phoneNumber
	 * @return updated <code>UserEntity</code> object.
	 */
	public UserEntity setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * Sets the user's fax number.
	 *
	 * @param faxNumber
	 * @return updated <code>UserEntity</code> object.
	 */
	public UserEntity setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
		return this;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Sets the user's mobile number.
	 *
	 * @param mobileNumber
	 * @return updated <code>UserEntity</code> object.
	 */
	public UserEntity setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}


	public String getOther() {
		return other;
	}

	public UserEntity setOther(String other) {
		this.other = other;
		return this;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public UserEntity setAddress(AddressEntity address) {
		this.address = address;
		return this;
	}

	/**
     * Gets the user's username.
     *
     * @return {@link #email}
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // region <OBJECT>


}
