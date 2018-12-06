package com.bw.ymy.mvptext1.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Netius {
    private static Netius insatnce;

    public Netius() {
    }

    public static  Netius getInsatnce()
    {
        if(insatnce==null)
        {
            insatnce=new Netius();
        }
        return  insatnce;
    }

    public  interface  Callback<T>
    {
        void  onsuccess(T t);
    }
    //是否可以用网络
    public  boolean hasNetWork(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return  activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
    }
    //判断是否是手机网络
    public  boolean isMobile(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return  activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE;

    }

    public  void  requestData(final  String urlstr,final  Class clazz,final Callback callback)
    {
        new AsyncTask<String,Void,Object>()
        {
            @Override
            protected Object doInBackground(String... strings) {
                return requestData(urlstr,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callback.onsuccess(o);
            }
        }.execute(urlstr);
    }
    public <T> T requestData(String  urlstr,Class clazz)
    {
        return (T)new Gson().fromJson(requestData(urlstr),clazz);
    }

    public  String requestData(String urlstr)
    {
        String result="";

        try {
            URL url=new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                String s = streamTostring(urlConnection.getInputStream());
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public  String streamTostring(InputStream is)throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder=new StringBuilder();
        for (String tmp=bufferedReader.readLine();tmp!=null;tmp=bufferedReader.readLine()){
            builder.append(tmp);
        }
        return builder.toString();

    }
}
