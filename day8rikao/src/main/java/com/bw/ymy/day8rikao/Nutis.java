package com.bw.ymy.day8rikao;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Nutis {
    private  static  Nutis instance;

    public Nutis() {
    }

    public  static  Nutis getInstance()
    {
        if(instance==null)
        {
            instance=new Nutis();
        }
        return  instance;
    }
    public  interface  callback<T>
    {
        void  onsuccess(T t);
    }
    public  void getRequest(final  String urlstr,final  Class clazz,final callback callback)
    {
        new AsyncTask<String,Void,Object>()
        {
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(urlstr,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
              callback.onsuccess(o);
            }
        }.execute(urlstr);
    }
    public  <T> T getRequest(String urlstr,Class clazz)
    {
        return  (T) new Gson().fromJson(getRequest(urlstr),clazz);
    }
    public  String getRequest(String urlstr)
    {
        String result="";
        try {
            URL url=new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //读取小超市
            urlConnection.setReadTimeout(5000);
            //连接超时
            urlConnection.setConnectTimeout(5000);
            //成功
            int responseCode = urlConnection.getResponseCode();
        if(responseCode==200)
        {

            result=stringeam2(urlConnection.getInputStream());
        }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;

    }

    private String stringeam2(InputStream inputStream)throws IOException {

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        for (String tmp=bufferedReader.readLine();tmp!=null;tmp=bufferedReader.readLine())
        {
            stringBuilder.append(tmp);
        }
        return  stringBuilder.toString();
    }
}
