package com.bw.ymy.mvp1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.ymy.mvp1.Bean.DengBean;
import com.bw.ymy.mvp1.R;
import com.bw.ymy.mvp1.presenter.Ipresentermpl;
import com.bw.ymy.mvp1.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private EditText etname,etpass;
    Ipresentermpl ipresentermpl;
    private Button login;

    private String name;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //获取资源id
        etname=findViewById(R.id.etname);
        etpass=findViewById(R.id.etpass);
        login=findViewById(R.id.login);

        //实劣化P
        ipresentermpl=new Ipresentermpl(this);

        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id)
        {
            case  R.id.login:
                name=etname.getText().toString();
                pass=etpass.getText().toString();
                ipresentermpl.startRequest("http://120.27.23.105/user/login?mobile="+name+"&password="+pass,DengBean.class);
                break;

        }

    }

    @Override
    public void onsuccess(Object data) {
        DengBean dengBean= (DengBean) data;

        //判断
        if(dengBean.getCode().equals("0"))
        {

                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,loginActivity.class);
           // intent.putExtra("name",)
                startActivity(intent);

        }

    }

    @Override
    public void shi(String msg) {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
    }
    //解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ipresentermpl.detach();
    }
}
