package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "differences")
public class DifferenceEntity implements Serializable {




    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private long id;

    @Column(name="timestamp")
    private Long timestampMillis;

    @Column(name="difference")
    private Double difference;

    @Column(name = "Scenario_ID")
    private String testScenarioID;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTestScenarioID() {
        return testScenarioID;
    }
    public void setTestScenarioID(String testScenarioID) {
        this.testScenarioID = testScenarioID;
    }

    public Long getTimestampMillis() {
        return timestampMillis;
    }
    public void setTimestampMillis(Long timestampMillis) {
        this.timestampMillis = timestampMillis;
    }

    public Double getDifference() {
        return difference;
    }
    public void setDifference(Double difference) {
        this.difference = difference;
    }
}

