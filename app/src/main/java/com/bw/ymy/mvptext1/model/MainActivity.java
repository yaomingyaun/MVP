package com.bw.ymy.mvptext1.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.ymy.mvptext1.R;
import com.bw.ymy.mvptext1.presemter.Login;
import com.bw.ymy.mvptext1.view.IView;

/**
 * 1.首先创建一个 Interstance View接口类
 * 2.主页面继承 Activity implements View.OnClickListener, 上一步写的类
 *
 *
 *
 */

public class MainActivity extends Activity implements View.OnClickListener,IView {

    EditText etname,etpass;
    //P
    Login mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个方法，获取资源id
        initView();
        //创建一个实列
        initPersenter();
    }

    private void initPersenter() {
        mlogin=new Login(this);
    }

    //获取资源id
    private void initView() {
        etname=findViewById(R.id.etname);
        etpass=findViewById(R.id.etpass);
        //无效  作废
        etpass.invalidate();
        //点击登录       http://120.27.23.105/user/login?mobile=手机号&password=密码
        findViewById(R.id.deng).setOnClickListener(this);
        //点击注册    http://120.27.23.105/user/reg?mobile=手机号&password=密码
        findViewById(R.id.zhu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id)
        {
            //点击登录
            case R.id.deng:
                //吧User类拿过来 引用
                User user=new User(etname.getText().toString(),etpass.getText().toString());

                mlogin.submit(user);
                break;
                //点击注册
            case R.id.zhu:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();


        mlogin.detachView();
    }

        //成功
    @Override
    public void success(Object data) {
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
    }
        //失败
     @Override
    public void fail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
