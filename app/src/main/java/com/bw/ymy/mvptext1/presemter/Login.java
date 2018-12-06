package com.bw.ymy.mvptext1.presemter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.bw.ymy.mvptext1.Bean.LoginBean;
import com.bw.ymy.mvptext1.Utils.Netius;
import com.bw.ymy.mvptext1.model.User;
import com.bw.ymy.mvptext1.view.IView;

public class Login {

    //把第一个实列拿过来
    private IView mIView;

    public  Login(@NonNull IView iView)
    {
        mIView=iView;
    }
    public  void  submit(User user)
    {
        if(checkName(user.getName())&&checkPass(user.getPass()))
        {
        Netius.getInsatnce().requestData("http://120.27.23.105/user/login?mobile="+user.getName()+"&password="+user.getPass(),
                LoginBean.class,new Netius.Callback<LoginBean>(){
                    @Override
                    public void onsuccess(LoginBean loginBean) {
                        if(loginBean.getCode()==0)
                        {
                            mIView.success(loginBean);
                        }else
                        {
                            mIView.fail("账号密码失败");
                        }
                    }
                });
        }
        else
        {
            mIView.fail("账号或者密码错误");
        }
    }

    public  void  detachView()
    {
        mIView=null;
    }
    //判断账号
    private  boolean checkName(String name)
    {
        return  !TextUtils.isEmpty(name);
    }
    //判断密码大于6位
    private  boolean checkPass(String pass)
    {
        return (!TextUtils.isEmpty(pass)&&pass.length()>=6);
    }

}
