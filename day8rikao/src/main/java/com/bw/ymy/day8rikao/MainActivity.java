package com.bw.ymy.day8rikao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends AppCompatActivity {
    private PullToRefreshListView pull;
    private  MyBase adapter;
    private  int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull=findViewById(R.id.pull);

        adapter=new MyBase(this);
        pull.setAdapter(adapter);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                page=1;
                inlodata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            inlodata();
            }
        });
        page++;
        inlodata();
    }
    private  String  url="http://www.zhaoapi.cn/product/getProducts?pscid=1";
    private  void inlodata()
    {
        Nutis.getInstance().getRequest(url,Bean.class,new Nutis.callback<Bean>(){
            @Override
            public void onsuccess(Bean bean) {
                if(page==1)
                {
                    adapter.setlist(bean.getData());
                }else
                {
                    adapter.setadd(bean.getData());
                }
                pull.computeScroll();
            }
        });
    }
}
