package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Serozh on 6/7/2016.
 */
@Entity
@Table(name = "process_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"process_id", "language_id"})
})
public class ProcessInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="process_id", foreignKey =  @ForeignKey(name="FK_process"))
    private ProcessEntity process;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_process_lang"))
    private LanguageEntity language;

    //process title
    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public ProcessInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ProcessInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProcessEntity getProcess() {
        return process;
    }

    public ProcessInfoEntity setProcess(ProcessEntity process) {
        this.process = process;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public ProcessInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProcessInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ProcessInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "ProcessInfoEntity{" +
                "id=" + id +
                ", name ='" + name + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
