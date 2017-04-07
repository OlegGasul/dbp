package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.BigBags;
import de.mexchange.packagingdb.domain.ContainerPhoto;

public interface BigBagsService extends ModelService<BigBags> {

    void add(long containerID, ContainerPhoto photo) throws DataNotFoundException, InternalErrorException;

    ContainerPhoto getPhotoByID(Long id) throws DataNotFoundException, InternalErrorException;

	void deletePhotoByID(Long id) throws DataNotFoundException, InternalErrorException;
}
