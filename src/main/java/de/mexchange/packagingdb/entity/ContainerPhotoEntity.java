package de.mexchange.packagingdb.entity;


import javax.persistence.*;

/**
 * Cubic Container Photos Entity class
 */
@Entity
@Table(name = "container_photo")
public class ContainerPhotoEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "filename")
    private String filename;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name="container_id")
    private ContainerEntity container;

    /**
     * Initializes a new instance of the class.
     */
    public ContainerPhotoEntity() {
    }

    // region <GET/SET>


    public Long getId() {
        return id;
    }

    public ContainerPhotoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public ContainerPhotoEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public ContainerPhotoEntity setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ContainerPhotoEntity setData(byte[] data) {
        this.data = data;
        return this;
    }

    public ContainerEntity getContainer() {
        return container;
    }

    public ContainerPhotoEntity setContainer(ContainerEntity container) {
        this.container = container;
        return this;
    }

    /**
     * @return string representation of <code>BulkPhotoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CubicPhotoEntity{" +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", id=" + id +
                '}';
    }
    //endregion
}
