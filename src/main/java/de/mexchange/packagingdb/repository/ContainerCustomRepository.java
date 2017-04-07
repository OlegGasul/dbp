package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

/**
 * Created by Arthur on 7/13/2016.
 */
public interface ContainerCustomRepository<ContainerEntity, Long> {

    List<ContainerEntity> inheritedSearch(String operator, Map queryMap) throws InternalErrorException;

    Long inheritedCountSearch(String operator, Map queryMap) throws InternalErrorException;
}
