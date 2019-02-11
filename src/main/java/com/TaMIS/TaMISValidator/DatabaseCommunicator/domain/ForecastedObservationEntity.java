package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain;




import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "wps_results")
public class ForecastedObservationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private long id;

    @Column(name="timestamp")
    private Long timestampMillis;

    @Column (name="pred_value")
    private Double predictedSeepageValue;

    public String getTestScenarioID() {
        return testScenarioID;
    }

    public void setTestScenarioID(String testScenarioID) {
        this.testScenarioID = testScenarioID;
    }

    @Column (name="Scenario_ID")
    private String testScenarioID;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() { return timestampMillis; }
    public void setTimestamp(long timestampMillis) { this.timestampMillis = timestampMillis; }

    public Double getPredictedSeepageValue() { return predictedSeepageValue; }
    public void setPredictedSeepageValue(Double predictedSeepageValue) { this.predictedSeepageValue = predictedSeepageValue; }



}


