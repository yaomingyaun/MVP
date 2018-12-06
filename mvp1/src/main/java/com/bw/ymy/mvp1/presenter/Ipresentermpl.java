package com.bw.ymy.mvp1.presenter;

import com.bw.ymy.mvp1.callback.MyCallBack;
import com.bw.ymy.mvp1.model.Imodelmpl;
import com.bw.ymy.mvp1.view.IView;

public class Ipresentermpl implements  Ipresenter {

    private IView miview;
    private Imodelmpl model;

    public Ipresentermpl(IView iView) {
        this.miview = iView;
        model = new Imodelmpl();
    }
    //解绑
    public  void  detach()
    {
        miview=null;
        model=null;
    }

    @Override
    public void startRequest(String url, Class clazz) {

        model.Request(url, clazz, new MyCallBack() {
            @Override
            public void onsuccess(Object o) {
                miview.onsuccess(o);
            }
        });

    }
}
