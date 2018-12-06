package com.bw.ymy.mvptext1.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.ymy.mvptext1.R;
import com.bw.ymy.mvptext1.presemter.Sing;
import com.bw.ymy.mvptext1.view.IView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,IView
{
    private EditText ename,epass;
    Sing sing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
        initpersenter();
    }

    private void initpersenter() {
        sing=new Sing(this);
    }

    private void initview() {
        //获取资源id
        ename=findViewById(R.id.ename);
        epass=findViewById(R.id.epass);
        epass.invalidate();
        findViewById(R.id.sing).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.sing:
                User user=new User(ename.getText().toString(),epass.getText().toString());
                sing.submit(user);
                break;
        }

    }

    @Override
    public void success(Object data) {

        Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
