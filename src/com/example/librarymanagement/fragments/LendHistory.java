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
import com.example.librarymanagement.adapters.bookListAdapter;
import com.example.librarymanagement.http.httpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ligan_000 on 2014/10/22.
 */
//用户历史借阅列表。
public class LendHistory extends Fragment {

    private ListView listView;
    private TextView tip_of_login;
    public static ImageView refresh;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getActivity(), "获取历史记录失败：" + msg.what, Toast.LENGTH_SHORT).show();
            } else {
                String res = msg.obj.toString();
                JSONArray array = null;
                JSONObject object;
                HashMap<String,String> map;
                try {
                    array = new JSONArray(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        object = array.getJSONObject(i);
                        map = new HashMap<String, String>();
                        map.put("book_class_name",object.getString("book_class_name"));
                        map.put("lend_time",object.getString("lend_time"));
                        map.put("return_time",object.getString("return_time"));
                        list.add(map);
                        Log.i("list add ",list.get(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listView.setAdapter(new bookListAdapter(getActivity(),list));
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
            listView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "亲！还没登录！", Toast.LENGTH_SHORT).show();
        } else {
            tip_of_login.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            //加载用户的借阅历史。
            String now_user = LoginState.now_user;
            if (now_user != null) {
                httpRequest http = new httpRequest();
                http.selectUserHistory(handler, now_user);
            }

        }
        super.onResume();
    }


}
