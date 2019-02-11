package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs;

import com.TaMIS.TaMISValidator.SensorWebCommunicator.sosCommunicator.SosReader;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;

public class
SosReaderInput {

    private long id;
    private double observedSeepageValue;
    private long timestampMillis;

    @Autowired
    public SosReaderEntity toReaderEntity() {

        SosReaderEntity obs2 = new SosReaderEntity();
        obs2.setTimestamp(this.timestampMillis);
        obs2.setObservedSeepageValue(this.observedSeepageValue);

        return obs2;

    }

    public static ArrayList<SosReaderEntity> extractSosObservation() throws Exception {
        SosReaderEntity obs2 = new SosReaderEntity();
        SosReader sosReader = new SosReader();
        InputStream is = sosReader.forwardGetRequest();
        JSONObject lastValue = (JSONObject) sosReader.ParseResponse(is);
        ArrayList<SosReaderEntity> obsList = new ArrayList();

        obs2.setObservedSeepageValue((Double) lastValue.get("value"));
        obs2.setTimestamp((Long) lastValue.get("timestamp"));
        obsList.add(obs2);


        return obsList;
    }
}
