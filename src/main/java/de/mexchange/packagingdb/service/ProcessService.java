package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.Process;
import de.mexchange.packagingdb.domain.lcp.ProcessType;

import java.util.List;

public interface ProcessService extends ModelService<Process> {

    List<Process> getList(int page, ProcessType type) throws InternalErrorException;

    List<Process> getList(int page, int count,  ProcessType type) throws InternalErrorException;

    long getListCount(ProcessType type) throws InternalErrorException;
}
