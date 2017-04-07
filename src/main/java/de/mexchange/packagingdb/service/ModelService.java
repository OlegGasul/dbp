package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.lcp.Language;

import java.util.List;

public interface ModelService<T> {

	T add(T t) throws InternalErrorException;

	T edit(T t) throws DataNotFoundException, InternalErrorException;

	T getByID(Long id) throws DataNotFoundException, InternalErrorException;

	long getListCount() throws InternalErrorException;

	List<T> getAll(Language language) throws InternalErrorException;

	List<T> getList(int page, int count) throws InternalErrorException;

	List<T> typeahead(String info) throws InternalErrorException;

	void deleteByID(Long id) throws DataNotFoundException, InternalErrorException;

	void deleteByIDs(List<Long> ids) throws DataNotFoundException, InternalErrorException;
}
