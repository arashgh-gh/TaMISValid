package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "sos_observations")
public class SosReaderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private long id;

    @Column(name="timestamp")
    private long timestampMillis;

    @Column (name="observed_value")
    private Double observedSeepageValue;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() { return timestampMillis; }
    public void setTimestamp(long timestampMillis) { this.timestampMillis = timestampMillis; }

    public Double getObservedSeepageValue() { return observedSeepageValue; }
    public void setObservedSeepageValue(Double observedSeepageValue) { this.observedSeepageValue = observedSeepageValue; }

}
