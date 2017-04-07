package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.domain.AbstractModel;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all Services.
 */
public abstract class AbstractService <T extends AbstractModel, E extends AbstractEntity> {

    public abstract E toEntity(T dto);

    public abstract T fromEntity(E entity);

    public abstract T fromEntity(E entity, Language language);

    /**
     * Transforms list of transfer (DTO) objects into the list of corresponding entities.
     *
     * @param entities
     * @return
     */
    public List<T> fromEntities(List<E> entities) {
        List<T> dtoList = new ArrayList<>(entities.size());

        for (E entity : entities) {
            dtoList.add(fromEntity(entity));
        }

        return dtoList;
    }

    /**
     * Transforms list of transfer (DTO) objects into the list of corresponding entities.
     *
     * @param entities
     * @return
     */
    public List<T> fromEntities(List<E> entities, Language language) {
        List<T> dtoList = new ArrayList<>(entities.size());

        for (E entity : entities) {
            dtoList.add(fromEntity(entity, language));
        }

        return dtoList;
    }

    /**
     * Transforms list of entities into the list of corresponding DTO (transfer) objects.
     *
     * @param dtoList
     * @return
     */
    public List<E> toEntities(List<T> dtoList) {
        List<E> entities = new ArrayList<>(dtoList.size());

        for (T dto : dtoList) {
            entities.add(toEntity(dto));
        }

        return entities;
    }
}
