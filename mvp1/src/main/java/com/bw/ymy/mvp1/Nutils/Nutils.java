package com.bw.ymy.mvp1.Nutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Nutils {

    public  static  String  Request(String dataurl)
    {
        String result="";
        try {
            URL url=new URL(dataurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //设置连接类型
            urlConnection.setRequestMethod("GET");
            //连接超时
            urlConnection.setConnectTimeout(5000);
            //读取超时
            urlConnection.setReadTimeout(5000);
            //获取请求吗
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

    private static String stringeam2(InputStream is) throws  IOException
    {
        InputStreamReader inputStreamReader=new InputStreamReader(is);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        for (String tmp=bufferedReader.readLine();tmp!=null;tmp=bufferedReader.readLine())
        {
            stringBuilder.append(tmp);
        }
        return  stringBuilder.toString();
    }
}
