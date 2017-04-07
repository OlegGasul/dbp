package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.ConicalClampingRing;
import de.mexchange.packagingdb.domain.ContainerPhoto;

public interface ConicalClampingRingsService extends ModelService<ConicalClampingRing> {

    void add(long cylindricalID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException;

    ContainerPhoto getPhotoByID(Long id) throws DataNotFoundException, InternalErrorException;

	void deletePhotoByID(Long id) throws DataNotFoundException, InternalErrorException;
}
