package com.bw.ymy.mvptext1.view;

public interface IView<T> {

    //成功
    void success(T data);
    //失败
    void fail(String msg);
}
