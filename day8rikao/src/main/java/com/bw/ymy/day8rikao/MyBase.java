package com.bw.ymy.day8rikao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {

    private List<Bean.DataBean> mdata;
    private Context context;

    public MyBase( Context context) {
        mdata = new ArrayList<>();
        this.context = context;
    }
    public  void  setlist(List<Bean.DataBean> datas)
    {
        mdata.clear();
        mdata.addAll(datas);
        notifyDataSetChanged();
    }
    public  void  setadd(List<Bean.DataBean> datas)
    {

        mdata.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.item1,parent,false);
            holder=new ViewHolder();
            holder.title1=convertView.findViewById(R.id.title1);
            holder.title2=convertView.findViewById(R.id.title2);
            convertView.setTag(holder);
        }else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title1.setText(mdata.get(position).getTitle());
       // holder.title2.setText((int) mdata.get(position).getPrice());
        return convertView;
    }
    class  ViewHolder{
        TextView title1,title2;
    }
}
