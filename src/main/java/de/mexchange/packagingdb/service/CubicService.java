package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.Cubic;

/**
 * Created by Garik on 6/15/16.
 */
public interface CubicService extends ModelService<Cubic> {

    void add(long bulkID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException;

    ContainerPhoto getPhotoByID(Long id) throws DataNotFoundException, InternalErrorException;

	void deletePhotoByID(Long id) throws DataNotFoundException, InternalErrorException;
}
