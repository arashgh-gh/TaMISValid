package com.TaMIS.TaMISValidator.SensorWebCommunicator.sosCommunicator;


import com.TaMIS.TaMISValidator.ApplicationUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import org.json.simple.JSONObject;

import java.io.*;
import java.net.URI;
import java.util.Scanner;

import static org.quartz.JobBuilder.newJob;


public class SosReader {

    private static final String LOGFILE = "LogInfo.txt";
    private static final String REALM = "TAMIS";
    private static final String AUTHORTYPE = "basic";
    private static final String HOST = "fluggs.wupperverband.de";
    private static final String SOSURL = "http://fluggs.wupperverband.de/sos2-tamis/api/v1/timeseries/451";
    private static final String DIRECTORY = "output/sosReader";
    private static final String PARTJSON = "_partJson.json";
    private static final String COMPLETEJSON = "_completeJson.json";


    private BasicCredentialsProvider createCredentialsProvider() throws FileNotFoundException {
        String username = null;
        String password = null;
        Scanner in = new Scanner(new FileReader(LOGFILE));
        in.useDelimiter("\\Z");
        in.useDelimiter("=|\\n");
        if (in.hasNext()) {
            String pass = in.next();
            password = in.next().trim();
            String user = in.next();
            username = in.next().trim();

        }

        String realm = REALM;
        String authType = AUTHORTYPE;
        String host = HOST;
        int port = 80;

        AuthScope authScope = new AuthScope(host, port, realm, authType);
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);


        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(authScope, creds);

        return credentialsProvider;
    }


    public InputStream forwardGetRequest() throws Exception {


        String sosUrl = SOSURL;
        URI requestUrl = new URI(sosUrl);


        AuthCache authCache = new BasicAuthCache();
        HttpClientContext context = HttpClientContext.create();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        context.setCredentialsProvider(createCredentialsProvider());
        context.setAuthCache(authCache);
        HttpGet httpget = new HttpGet(requestUrl);

        InputStream is = null;
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        is = response.getEntity().getContent();
        return is;

    }

    public void responseSaver(InputStream is) throws Exception {

        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
        JSONObject lastValue = (JSONObject) jsonObject.get("lastValue");


        //Map newJson = new LinkedHashMap(); //if an ordered Json is necessary!!!
        JSONObject newJson = new JSONObject();

        newJson.put("last_timestap", lastValue.get("timestamp"));
        newJson.put("last_value", lastValue.get("value"));



    }

    public JSONObject ParseResponse(InputStream is) throws Exception {

        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
        JSONObject lastValue = (JSONObject) jsonObject.get("lastValue");
        return lastValue;
    }





}
