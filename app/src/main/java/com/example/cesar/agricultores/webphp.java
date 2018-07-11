package com.example.cesar.agricultores;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Cesar
 * Created by Cesar on 05/02/2018.
 *
 */

public class webphp extends AsyncTask<String, Void, String> {
    Context context;
    public AsyncResponse delegate = null;
    public AsyncResponse delegate2 = null;
    protected void onPreExecute() {

    }

    protected String doInBackground(String... params) {

        try {
            String url1=params[0];
            URL url = new URL(url1);
            JSONObject postDataParams = new JSONObject();
            if(params.length>1) {
                String param1 = params[1];
                postDataParams.put("param1", param1);
                if(params.length>2){
                    String param2 = params[2];
                    postDataParams.put("param2", param2);
                    if(params.length>3){
                        String param3 = params[3];
                        postDataParams.put("param3", param3);
                        if(params.length>4){
                            String param4 = params[4];
                            postDataParams.put("param4", param4);
                            if(params.length>5){
                                String param5 = params[5];
                                postDataParams.put("param5", param5);
                                if(params.length>6){
                                    String param6 = params[6];
                                    postDataParams.put("param6", param6);
                                    if(params.length>7){
                                        String param7 = params[7];
                                        postDataParams.put("param7", param7);
                                        if(params.length>8){
                                            String param8 = params[8];
                                            postDataParams.put("param8", param8);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

            //postDataParams.put("nombre", nombre);
            // postDataParams.put("email", email);
            Log.e("paramss", postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            if(params.length>1)
                writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());

        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(delegate!=null)
            delegate.processFinish(result);
        if(delegate2!=null)
            delegate2.processFinish2(result);
        /*Toast.makeText(context.getApplicationContext(), "<-",
                Toast.LENGTH_LONG).show();
                */
    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}

