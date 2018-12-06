package com.bw.ymy.mvp1.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bw.ymy.mvp1.List.Bean;
import com.bw.ymy.mvp1.List.MyBase;
import com.bw.ymy.mvp1.List.New;
import com.bw.ymy.mvp1.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class one extends Fragment {
    private PullToRefreshListView pull;
    private MyBase adapter;
    private  int page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pull=view.findViewById(R.id.pull);
        adapter=new MyBase(getActivity());
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
                page++;
                inlodata();
            }
        });
        page=1;
        inlodata();
    }
    private  String url="http://www.xieast.com/api/news/news.php?page=1";
    private  void inlodata()
    {
        New.getInstance().getRequest(url,Bean.class,new New.Callback<Bean>()
        {
            @Override
            public void onssss(Bean bean) {
                if(page==1)
                {
                    adapter.setlist(bean.getData());
                }else
                {
                    adapter.setadd(bean.getData());
                }
                page++;
                pull.onRefreshComplete();
            }
        });
    }
}
