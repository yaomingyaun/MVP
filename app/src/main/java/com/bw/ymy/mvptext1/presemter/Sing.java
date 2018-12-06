package com.bw.ymy.mvptext1.presemter;

import com.bw.ymy.mvptext1.Bean.SingBean;
import com.bw.ymy.mvptext1.Utils.Netius;
import com.bw.ymy.mvptext1.model.User;
import com.bw.ymy.mvptext1.view.IView;

public class Sing {

    private IView iView;

    public Sing(IView iView) {
        this.iView = iView;
    }

    public  void submit(User user)
    {
        if(checkName(user.getName())&&checkPass(user.getPass()))
        {
            Netius.getInsatnce().requestData("http://120.27.23.105/user/reg?mobile="+user.getName()+"&password="+user.getPass(),
                    SingBean.class,new Netius.Callback<SingBean>(){
                        @Override
                        public void onsuccess(SingBean singBean) {

                            if(singBean.getCode()==0)
                            {
                                iView.success(singBean);
                            }else
                            {
                                iView.fail(singBean.getMsg());
                            }

                    }
                    });

        }else
        {
            iView.fail("请输入正确的账号和密码");
        }
    }
    //解绑
    public  void detach()
    {
        iView=null;
    }
    //判断密码不少于6位
    protected  boolean checkPass(String pass)
    {
        return  !pass.isEmpty()&&pass.length()==6;
    }
    //判断手机号大于11位
    private boolean checkName(String name)
    {
        return  !name.isEmpty()&&name.length()==11;
    }
}
