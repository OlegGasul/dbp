package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Aggregate State Info Entity class
 */
@Entity
@Table(name = "aggregate_state_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"aggregate_state_id", "language_id"})
})
public class AggregateStateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="aggregate_state_id", foreignKey =  @ForeignKey(name="FK_aggregate_state"))
    private AggregateStateEntity aggregateState;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_aggregate_state_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "state")
    private String state;

    /**
     * Initializes a new instance of the class.
     */
    public AggregateStateInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public AggregateStateInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AggregateStateEntity getAggregateState() {
        return aggregateState;
    }

    public AggregateStateInfoEntity setAggregateState(AggregateStateEntity aggregateState) {
        this.aggregateState = aggregateState;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public AggregateStateInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getState() {
        return state;
    }

    public AggregateStateInfoEntity setState(String state) {
        this.state = state;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>AggregateStateInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "AggregateStateInfoEntity{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
