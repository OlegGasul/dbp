package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.search.FieldInfo;
import de.mexchange.packagingdb.common.search.ValueOption;
import de.mexchange.packagingdb.domain.CommonContainer;

import java.util.List;

/**
 * Created by Serozh on 7/10/2016.
 */
public interface ContainerService {

    long getListCount(FieldInfo fieldInfo, ValueOption valueOption, String fieldValue) throws InternalErrorException;

    List<CommonContainer> search(int page, int count, FieldInfo fieldInfo,  ValueOption valueOption, String fieldValue) throws InternalErrorException;
}
