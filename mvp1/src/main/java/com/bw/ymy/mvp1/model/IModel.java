package com.bw.ymy.mvp1.model;

import com.bw.ymy.mvp1.callback.MyCallBack;

public interface IModel {

    void Request(String urlstr, Class clazz, MyCallBack callBack);
}
