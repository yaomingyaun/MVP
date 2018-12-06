package com.bw.ymy.mvp1.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ymy.mvp1.R;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class two extends Fragment {
    private ImageView image;
    private Button back;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.two,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image=view.findViewById(R.id.image);
        back=view.findViewById(R.id.back);
        sharedPreferences=getActivity().getSharedPreferences("User",Context.MODE_MULTI_PROCESS);
        editor=sharedPreferences.edit();
        shengcheng();
    }


    //上个页面名字传过来生成二维码
    private void shengcheng() {

        String cc=((loginActivity)getActivity()).cc();
        QRTask qrTask=new QRTask(getActivity(),image,cc);
        qrTask.execute(cc);
    }
    static class  QRTask extends AsyncTask<String,Void,Bitmap>
    {
        private WeakReference<Context> mcontext;
        private  WeakReference<ImageView> mimage;
        public  QRTask(Context context,ImageView imageView,String name)
        {
            mcontext=new WeakReference<>(context);
            mimage=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String str=params[0];
            if(TextUtils.isEmpty(str))
            {
                return null;
            }
            int size=100;
            return QRCodeEncoder.syncEncodeQRCode(str,size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if(bitmap!=null)
            {
                mimage.get().setImageBitmap(bitmap);
            }else
            {
                Toast.makeText(mcontext.get(),"失败",Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(bitmap);
        }
    }
}
