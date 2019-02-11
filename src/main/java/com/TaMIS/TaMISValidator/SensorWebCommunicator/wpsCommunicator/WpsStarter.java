package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;

import org.apache.http.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class WpsStarter {

    public static String sendPostRequest(String target, StringEntity body) throws Exception {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(target);
        httpPost.setHeader("Content-type", "application/json");

        //httpPost.getRequestLine();
        httpPost.setEntity(body);
        HttpResponse response = httpClient.execute(httpPost);

        // ****** reading the headers of the response from post request ******
        String jobLocation = extractLocationHeader(response);

        return jobLocation;
    }

    private static String extractLocationHeader(HttpResponse response) {

        // making a string out of the wps-link which was received from post request
        String outputLink = response.getFirstHeader("Location").getValue();
        return outputLink;
    }

}
