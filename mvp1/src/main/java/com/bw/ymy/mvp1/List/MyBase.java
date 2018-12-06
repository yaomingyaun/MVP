package com.bw.ymy.mvp1.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.bw.ymy.mvp1.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {

    private List<Bean.DataBean> mdata;
    private Context context;

    public MyBase(Context context) {
        mdata = new ArrayList<>();
        this.context = context;
    }

    //刷新
    public void setlist(List<Bean.DataBean> datas) {
        mdata.clear();
        mdata.addAll(datas);
        notifyDataSetChanged();
    }

    //加载
    public void setadd(List<Bean.DataBean> datas) {

        mdata.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Bean.DataBean getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            holder = new ViewHolder();
            holder.text1 = convertView.findViewById(R.id.title1);
            holder.icon1=convertView.findViewById(R.id.icon1);
            ImageLoader.getInstance().displayImage(getItem(position).getUrl(),holder.icon1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(mdata.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView text1;
                ImageView icon1,icon2,icon3;
    }

}
