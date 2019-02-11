package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;

import com.TaMIS.TaMISValidator.ApplicationUtils;
import org.json.simple.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WpsJobObserver {

    private static final String STATUS_INFO = "StatusInfo";
    private static final String STATUS = "Status";
    private static final String OUTPUT = "Output";
    private static final String GET = "GET";
    private static final String SUCCEEDED = "Succeeded";
    private static final String JOBID = "JobID";


    public String getOutputURL(String jobURL) throws Exception {
        JSONObject jobStatus = this.getJobStatus(jobURL);

        JSONObject results = (JSONObject) jobStatus.get(STATUS_INFO);
        String outputURL = (String) results.get(OUTPUT);
        return outputURL;

    }

    public JSONObject getJobStatus(String jobURL) throws Exception {

        URL link = new URL(jobURL);
        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setRequestMethod(GET);
        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(con.getInputStream());
        return jsonObject;
    }

    public JSONObject observeJobUntilFinish(String jobURL) throws Exception {

        int tries = 60;

        for (int i = 0; i < tries; i++) {

            JSONObject jobStatus = this.getJobStatus(jobURL);

            JSONObject results = (JSONObject) jobStatus.get(STATUS_INFO);
            String status = (String) results.get(STATUS);

            if (status.equalsIgnoreCase(SUCCEEDED)) {

                return jobStatus;

            }
            TimeUnit.SECONDS.sleep(1);

        }
        return null;
    }

    public String getJobId(String jobURL) throws Exception{
        JSONObject jobStatus = this.getJobStatus(jobURL);

        JSONObject results = (JSONObject) jobStatus.get(STATUS_INFO);
        String jobId = (String) results.get("JobID");
        return jobId;
    }



}
