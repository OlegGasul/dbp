package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.Cylindrical;

/**
 * Created by Garik on 6/17/16.
 */
public interface CylindricalService extends ModelService<Cylindrical> {

    void add(long cylindricalID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException;

    ContainerPhoto getPhotoByID(Long id) throws DataNotFoundException, InternalErrorException;

	void deletePhotoByID(Long id) throws DataNotFoundException, InternalErrorException;
}
