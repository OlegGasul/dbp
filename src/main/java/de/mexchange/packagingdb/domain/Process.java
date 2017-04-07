package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.ProcessType;
import de.mexchange.packagingdb.entity.ProcessEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Data Transfer Object representing {@link ProcessEntity}
 */
public class Process extends AbstractModel {

    private byte[] processFile;

    private String processFilename;

    private String processFileContentType;

    private String remarks;

    @NotNull(message = "{err.field.process.type.required}")
    private ProcessType processType;

    @Valid
    private List<ProcessInfo> infoList;

    private ProcessInfo currentInfo;

    public byte[] getProcessFile() {
        return processFile;
    }

    public void setProcessFile(byte[] processFile) {
        this.processFile = processFile;
    }

    public String getProcessFilename() {
        return processFilename;
    }

    public void setProcessFilename(String processFilename) {
        this.processFilename = processFilename;
    }

    public String getProcessFileContentType() {
        return processFileContentType;
    }

    public void setProcessFileContentType(String processFileContentType) {
        this.processFileContentType = processFileContentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ProcessInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ProcessInfo> infoList) {
        this.infoList = infoList;
    }

    public ProcessInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(ProcessInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }
}
