package com.example.librarymanagement.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class httpRequest {

    FinalHttp finalHttp;

    public FinalHttp getFinalHttp(){
        if (finalHttp == null){
            finalHttp = new FinalHttp();
            finalHttp.configCharset("UTF-8");
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
    public void login(Handler handler,String name,String psw){
        AjaxParams params = new AjaxParams();
        params.put("stu_number_or_idcard",name);
        params.put("psword",psw);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        final Message msg = handler.obtainMessage();
        finalHttp.post("http://192.168.0.100:8080/servlets/userLogin",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
    //查找用户借阅历史。
    public void selectUserHistory(final Handler handler, String stu_number_or_idcard){
        AjaxParams params = new AjaxParams();
        params.put("stu_number_or_idcard",stu_number_or_idcard);
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

    public void selectUserInfo(Handler handler,String name){
        AjaxParams params = new AjaxParams();
        params.put("stu_number_or_idcard",name);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        final Message msg = handler.obtainMessage();
        finalHttp.post("http://192.168.0.100:8080/servlets/selectUserInfo",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("查找返回的结果： ",result);
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    public void lendBook(final Handler handler,int book_id,String stu_number){
        AjaxParams params = new AjaxParams();
        params.put("book_id",book_id+"");
        params.put("stu_number",stu_number);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/lendBook",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("借阅返回的结果： ",result);
                Message msg =Message.obtain(handler);
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = Message.obtain(handler);
                msg.what = 0;
                msg.obj = errorNo;
                handler.sendMessage(msg);
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    public void returnBook(final Handler handler,String stu_number_or_id,String book_class_number){
        AjaxParams params = new AjaxParams();
        params.put("stu_number_or_id",stu_number_or_id);
        params.put("book_class_number",book_class_number);
        finalHttp = getFinalHttp();
        finalHttp.configTimeout(3000);
        finalHttp.post("http://192.168.0.100:8080/servlets/returnBook",params,new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String result = o.toString();
                Log.i("借阅返回的结果： ",result);
                Message msg =Message.obtain(handler);
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("查找出错的原因： ",errorNo+"");
                Message msg = Message.obtain(handler);
                msg.what = 0;
                msg.obj = errorNo;
                handler.sendMessage(msg);
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
