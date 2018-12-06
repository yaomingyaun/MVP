package com.bw.ymy.mvp1.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bw.ymy.mvp1.R;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends FragmentActivity {
    private ViewPager viewPager1;
    private RadioGroup radio1;
    private List<Fragment> list;

    //传到第二个页面所定义的名字
    private  String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取资源id
        viewPager1=(ViewPager) findViewById(R.id.viewpage1);
        radio1=(RadioGroup) findViewById(R.id.radio1);
        //添加
        list=new ArrayList<Fragment>();
        list.add(new one());
        list.add(new two());
       viewPager1.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int i) {
               return list.get(i);
           }

           @Override
           public int getCount() {
               return list.size();
           }
       });

       radio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId)
               {
                   case R.id.but1:
                       viewPager1.setCurrentItem(0);
                       break;
                   case R.id.but2:
                       viewPager1.setCurrentItem(1);
                       break;
               }
           }
       });
    }
    public  String cc()
    {
        return  name;
    }
}
