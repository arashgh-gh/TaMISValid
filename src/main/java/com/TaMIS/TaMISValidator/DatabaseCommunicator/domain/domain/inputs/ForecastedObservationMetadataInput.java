package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs;

import com.TaMIS.TaMISValidator.ApplicationUtils;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.ProcessMonitoringService;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsOutputsGetter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ForecastedObservationMetadataInput {
    private String jobId;
    private String timeseriesLabel;
    private Date dateOfJobStart;
    private String stationLabel;

    @Autowired
    public ForecastedObservationMetadataEntity toForecastedObservationMetadataEntity(){

        ForecastedObservationMetadataEntity meta = new ForecastedObservationMetadataEntity();
        meta.setDateOfJobStart(this.dateOfJobStart);
        meta.setJobId(this.jobId);
        meta.setStationLabel(this.stationLabel);
        meta.setTimeseriesLabel(this.timeseriesLabel);

        return meta;
    }

    public static ArrayList<ForecastedObservationMetadataEntity> extractMetadata() throws Exception {


        List<String> endpoint = ProcessMonitoringService.executeProcess();
        String link = endpoint.get(0);


        URL url = new URL(link);
        InputStream is = url.openStream();

        ArrayList<ForecastedObservationMetadataEntity> metaList = new ArrayList();
        List<String> m = new ArrayList<>();
        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
        String id = (String) jsonObject.get("id");
        String timeseriesLabel = String.valueOf(jsonObject.get("label"));
        JSONObject jsonObjectAsghar = (JSONObject) jsonObject.get("station");
        JSONObject prop = (JSONObject) jsonObjectAsghar.get("properties");
        String stationLabel = String.valueOf(prop.get("label"));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ForecastedObservationMetadataEntity meta = new ForecastedObservationMetadataEntity();


        meta.setJobId(id);
        meta.setTimeseriesLabel(timeseriesLabel);
        meta.setStationLabel(stationLabel);
        meta.setDateOfJobStart(timestamp);

        metaList.add(meta);
        return metaList;

    }

}
