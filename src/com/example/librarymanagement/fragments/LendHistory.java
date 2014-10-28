package com.example.librarymanagement.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.librarymanagement.R;
import com.example.librarymanagement.http.httpRequest;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class LendHistory extends Fragment {

    private ListView listView;
    private TextView tip_of_login;
    private ImageView refresh;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getActivity(), "获取历史记录失败：" + msg.what, Toast.LENGTH_SHORT).show();
            } else {
                String res = msg.obj.toString();
                JSONArray array = null;
                try {
                    array = new JSONArray(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<String> list = new ArrayList<String>();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        list.add(array.get(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lend_history, null);
        listView = (ListView) view.findViewById(R.id.lv_history);
        tip_of_login = (TextView) view.findViewById(R.id.tip_of_no_login);
        tip_of_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();
            }
        });
        refresh = (ImageView)view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        if (LoginState.now_user == null) {
            tip_of_login.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "亲！还没登录！", Toast.LENGTH_SHORT).show();
        } else {
            tip_of_login.setVisibility(View.GONE);
            //加载用户的借阅历史。
            String now_user = LoginState.now_user;
            if (now_user != null) {
                httpRequest http = new httpRequest();
                http.selectUserHistory(handler, now_user);
            }
            //listView.setAdapter(new ArrayAdapter<String>());

        }
        super.onResume();
    }


}
