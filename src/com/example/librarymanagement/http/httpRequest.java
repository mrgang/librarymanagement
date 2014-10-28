package com.example.librarymanagement.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.HashMap;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class httpRequest {

    FinalHttp finalHttp;

    public FinalHttp getFinalHttp(){
        if (finalHttp == null){
            finalHttp = new FinalHttp();
        }
        return finalHttp;
    }

    public void selectByName(final Handler handler, String str){
        AjaxParams params = new AjaxParams();
        params.put("book_name",str);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/selectByName",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ", result);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
    public void selectByAuthor(final Handler handler, String str){
        AjaxParams params = new AjaxParams();
        params.put("author",str);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/selectByAuthor",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
    public void selectByBookClassName(final Handler handler,String str){
        AjaxParams params = new AjaxParams();
        params.put("BookClassName",str);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/selectBookClassName",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
    //用户登录。
    public HashMap<String ,String> login(String name,String psw){
        final HashMap<String,String> map = new HashMap<String, String>();
        AjaxParams params = new AjaxParams();
        params.put("stu_number_or_idcard",name);
        params.put("psword",psw);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/userLogin",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                map.put("what","1");
                map.put("obj",result);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                map.put("what","0");
                map.put("obj",errorNo+"");
                super.onFailure(t, errorNo, strMsg);
            }
        });
        return map;
    }
    //查找用户借阅历史。
    public void selectUserHistory(final Handler handler, String user_name){
        AjaxParams params = new AjaxParams();
        params.put("user_name",user_name);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/selectUserLendHistory",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
