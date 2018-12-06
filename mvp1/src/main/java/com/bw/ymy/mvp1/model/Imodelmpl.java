package com.bw.ymy.mvp1.model;

import android.os.AsyncTask;

import com.bw.ymy.mvp1.Nutils.Nutils;
import com.bw.ymy.mvp1.callback.MyCallBack;
import com.google.gson.Gson;

//基础上一个Model
public class Imodelmpl implements  IModel {
    //创建callback
    public  <T> T Request(String urlstr,Class clazz)
    {
        //调用工具类
        return  (T)new Gson().fromJson(Nutils.Request(urlstr),clazz);
    }


    @Override
    public void Request(String urlstr, final Class clazz, final MyCallBack callBack) {
        new AsyncTask<String,Void,Object>()
        {
            @Override
            protected Object doInBackground(String... strings) {

                //调用上面的
                return Request(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
               callBack.onsuccess(o);
            }
        }.execute(urlstr);

    }
}
