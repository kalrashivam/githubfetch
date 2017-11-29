package com.example.engineers_workbook.fetchinggithub;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by hp on 29-11-2017.
 */

public class QueryUtils {

    public static URL CreateUrl(String Stringurl){

        URL url =null;
        try{
            url = new  URL(Stringurl);
        }catch (MalformedURLException e){
            Log.e("Check URL", "No data"+ e);
        }
        return url;
    }

    public static String getResponseFromHttp(URL url) throws IOException{
        HttpURLConnection urlConnection =  (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasinput = scanner.hasNext();
            if (hasinput) {
                return scanner.next();
            } else
                return null;
        }finally {
            urlConnection.disconnect();
        }
    }



}
