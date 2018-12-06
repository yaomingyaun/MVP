package com.bw.ymy.mvp1.List;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class New {
    private  static New instance;

    public New() {
    }

    public static  New getInstance()
    {
        if(instance==null)
        {
            instance=new New();
        }
        return  instance;
    }
    public  interface  Callback<T>
    {
        void onssss(T t);
    }
    public  void getRequest(final  String urlstr,final  Class clazz,final Callback callback)
    {
        new AsyncTask<String,Void,Object>()
        {
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(urlstr,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callback.onssss(o);
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
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200)
            {
                result=Stringeam(urlConnection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  result;
    }

    private String Stringeam(InputStream inputStream)throws IOException{
        InputStreamReader InputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(InputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        for (String tmp=bufferedReader.readLine();tmp!=null;tmp=bufferedReader.readLine())
        {
            stringBuilder.append(tmp);
        }
        return  stringBuilder.toString();
    }
}
