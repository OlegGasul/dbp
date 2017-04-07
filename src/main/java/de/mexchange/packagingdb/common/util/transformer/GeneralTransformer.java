package de.mexchange.packagingdb.common.util.transformer;

import de.mexchange.packagingdb.domain.AbstractModel;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.entity.AbstractEntity;
import de.mexchange.packagingdb.entity.UserEntity;

/**
 * User: Garik
 * Date: 6/17/16
 * Time: 10:49 PM
 */
public class GeneralTransformer {

    /**
     * Transfer createdBy & modifiedBy users data from entity to model object
     * @param entity
     * @param model
     */
    protected static void fromEntityToModel(AbstractEntity entity, AbstractModel model) {

        if (entity.getCreatedBy() != null) {
            User createdBy = new User();
            createdBy.setId(entity.getCreatedBy().getId());
            model.setCreatedBy(createdBy);
        }

        if (entity.getModifiedBy() != null) {
            User modifiedBy = new User();
            modifiedBy.setId(entity.getModifiedBy().getId());
            model.setModifiedBy(modifiedBy);
        }
    }

    /**
     * Transfer createdBy & modifiedBy users data from model to entity object
     * @param model
     * @param entity
     */
    protected static void fromModelToEntity(AbstractModel model, AbstractEntity entity) {

        if (model.getCreatedBy() != null) {
            UserEntity createdBy = new UserEntity();
            createdBy.setId(model.getCreatedBy().getId());
            entity.setCreatedBy(createdBy);
        }

        if (model.getModifiedBy() != null) {
            UserEntity modifiedBy = new UserEntity();
            modifiedBy.setId(model.getModifiedBy().getId());
            entity.setModifiedBy(modifiedBy);
        }
    }
}
