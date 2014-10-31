package com.example.librarymanagement.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.librarymanagement.R;
import com.example.librarymanagement.http.httpRequest;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ligan_000 on 2014/10/22.
 */
//用户登录状态。
public class LoginState extends Fragment {

    public static String now_user = null;
    private String name,qq;
    private EditText userName;
    private EditText psw;
    private Button login;

    private TextView txt_user_name,stu_number,stu_qq;
    private Button btn_login_out;

    private LinearLayout layout1, layout2;
    private httpRequest http = new httpRequest();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getActivity(), "链接服务器出错：" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
            } else if (msg.obj.toString().trim().equals("false")) {
                Toast.makeText(getActivity(), "请检查用户名和密码", Toast.LENGTH_SHORT).show();
            } else {
                now_user = userName.getText().toString();
                http.selectUserInfo(new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(msg.obj.toString());
                            name = object.getString("stu_name");
                            now_user = object.getString("stu_number_or_idcard");
                            qq = object.getString("qq");
                            txt_user_name.setText(name);
                            stu_number.setText(now_user);
                            stu_qq.setText(qq);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },now_user);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_state, null);
        userName = (EditText) view.findViewById(R.id.user_name);
        psw = (EditText) view.findViewById(R.id.user_password);
        login = (Button) view.findViewById(R.id.btn_login_in);
        txt_user_name = (TextView)view.findViewById(R.id.txt_user_name);
        stu_number = (TextView)view.findViewById(R.id.txt_stu_number);
        stu_qq = (TextView)view.findViewById(R.id.txt_stu_qq);
        btn_login_out = (Button) view.findViewById(R.id.btn_login_out);
        layout1 = (LinearLayout) view.findViewById(R.id.login_out_state);
        layout2 = (LinearLayout) view.findViewById(R.id.login_in_state);

        if (now_user != null) {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        } else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                http.login(handler,userName.getText().toString(), psw.getText().toString());
            }
        });

        btn_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now_user = null;
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        if (now_user != null) {
            txt_user_name.setText(name);
            stu_number.setText(now_user);
            stu_qq.setText(qq);
        }
        super.onResume();
    }
}
