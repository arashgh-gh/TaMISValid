package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wps_metadata")
public class ForecastedObservationMetadataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private long id;

    @Column(name="jobid")
    private String jobId;


    @Column(name="label")
    private String timeseriesLabel;



    @Column(name="station")
    private String stationLabel;

    @Column (name="date")
    private Date dateOfJobStart;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getDateOfJobStart() {
        return dateOfJobStart;
    }

    public void setDateOfJobStart(Date dateOfJobStart) {
        this.dateOfJobStart = dateOfJobStart;
    }

    public String getTimeseriesLabel() {
        return timeseriesLabel;
    }

    public void setTimeseriesLabel(String timeseriesLabel) {
        this.timeseriesLabel = timeseriesLabel;
    }

    public String getStationLabel() {
        return stationLabel;
    }

    public void setStationLabel(String stationLabel) {
        this.stationLabel = stationLabel;
    }

}
