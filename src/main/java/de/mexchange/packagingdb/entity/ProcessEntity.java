package de.mexchange.packagingdb.entity;

import de.mexchange.packagingdb.domain.lcp.ProcessType;
import de.mexchange.packagingdb.entity.converter.ProcessTypeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Serozh on 6/7/2016.
 */
@Entity
@Table(name = "process")
public class ProcessEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy="process", cascade = CascadeType.ALL)
    private Set<ProcessInfoEntity> infoList;

    @Lob
    @Column(name = "process_file")
    private byte[] processFile;

    @Column(name = "process_filename")
    private String processFilename;

    @Column(name = "process_file_content_type")
    private String processFileContentType;

    @NotNull
    @Column(name = "process_type", nullable = false)
    @Convert(converter = ProcessTypeConverter.class)
    private ProcessType processType;

    /**
     * Initializes a new instance of the class.
     */
    public ProcessEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ProcessEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public ProcessEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Set<ProcessInfoEntity> getInfoList() {
        return infoList;
    }

    public ProcessEntity setInfoList(Set<ProcessInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public byte[] getProcessFile() {
        return processFile;
    }

    public ProcessEntity setProcessFile(byte[] processFile) {
        this.processFile = processFile;
        return this;
    }

    public String getProcessFilename() {
        return processFilename;
    }

    public ProcessEntity setProcessFilename(String processFilename) {
        this.processFilename = processFilename;
        return this;
    }

    public String getProcessFileContentType() {
        return processFileContentType;
    }

    public ProcessEntity setProcessFileContentType(String processFileContentType) {
        this.processFileContentType = processFileContentType;
        return this;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public ProcessEntity setProcessType(ProcessType processType) {
        this.processType = processType;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ProcessEntity</code> object.
     */
    @Override
    public String toString() {
        return "ProcessEntity{" +
                "id=" + id +
                ", remarks='" + remarks + '\'' +
                ", processFilename='" + processFilename + '\'' +
                ", processFileContentType='" + processFileContentType + '\'' +
                ", processType='" + processType + '\'' +
                ", infoList=" + infoList +
                '}';
    }
    // endregion
}

