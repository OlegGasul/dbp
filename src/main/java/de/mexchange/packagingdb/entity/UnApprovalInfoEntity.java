package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

/**
 * UnAuthorization Info Entity class.
 */
@Entity
@Table(name = "authorization_room", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"authorization_id", "language_id"})
})
public class UnApprovalInfoEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="authorization_id", foreignKey =  @ForeignKey(name="FK_authorization"))
    private UnApprovalEntity authorization;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_authorization_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "roomName")
    private String roomName;

    /**
     * Initializes a new instance of the class.
     */
    public UnApprovalInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnApprovalEntity getAuthorization() {
        return authorization;
    }

    public void setAuthorization(UnApprovalEntity authorization) {
        this.authorization = authorization;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnApprovalInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnApprovalInfoEntity{" +
                "id=" + id +
                ", language=" + language +
                ", roomName='" + roomName + '\'' +
                '}';
    }
    // endregion


}
