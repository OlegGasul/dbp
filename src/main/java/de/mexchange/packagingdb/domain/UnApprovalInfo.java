package de.mexchange.packagingdb.domain;


import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnApprovalInfoEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Data UnApprovalInfo Object representing {@link UnApprovalInfoEntity}
 */
public class UnApprovalInfo {

    private Long id;

    @NotNull(message = "{err.field.approval.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.approval.room.name.required}")
    private String roomName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
