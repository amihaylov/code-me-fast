package com.boyko.codemefast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionUtils {
    public static String serverUrl = "http://localhost:8080/";

    public static String getRequest(String apiUrl) throws IOException {
        String url = serverUrl + apiUrl;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse getResponse = httpclient.execute(httpGet);
        String response = "";
        try {
            System.out.println(getResponse.getStatusLine());
            HttpEntity entity = getResponse.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                response += inputLine;
            }
            br.close();
            EntityUtils.consume(entity);
        } finally {
            getResponse.close();
        }
        return response;
    }

    public static String postRequest(String apiUrl, List<NameValuePair> urlParameters) throws IOException {
        String url = serverUrl + apiUrl;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
        CloseableHttpResponse postResponse = httpclient.execute(httpPost);
        String response = "";
        try {
            System.out.println(postResponse.getStatusLine());
            HttpEntity entity = postResponse.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                response += inputLine;
            }
            br.close();
            EntityUtils.consume(entity);
        } finally {
            postResponse.close();
        }
        return response;
    }

    public static String deleteRequest(String apiUrl) throws IOException {
        String url = serverUrl + apiUrl;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse getResponse = httpclient.execute(httpDelete);
        String response = "";
        try {
            System.out.println(getResponse.getStatusLine());
            HttpEntity entity = getResponse.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                response += inputLine;
            }
            br.close();
            EntityUtils.consume(entity);
        } finally {
            getResponse.close();
        }
        return response;
    }

    public static String putRequest(String apiUrl) throws IOException {
        String url = serverUrl + apiUrl;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        CloseableHttpResponse getResponse = httpclient.execute(httpPut);
        String response = "";
        try {
            System.out.println(getResponse.getStatusLine());
            HttpEntity entity = getResponse.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                response += inputLine;
            }
            br.close();
            EntityUtils.consume(entity);
        } finally {
            getResponse.close();
        }
        return response;
    }
}
