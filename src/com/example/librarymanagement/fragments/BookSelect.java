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
import com.example.librarymanagement.adapters.bookInfoAdapter;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.http.httpRequest;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class BookSelect extends Fragment {
    private static String[] itemp = {"书名", "作者", "分类号"};
    private ListView listView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int flag = msg.what;
            Log.i("BookSelect : 获取的查询结果是 ",msg.obj.toString());
            if (flag == 0){
                Toast.makeText(getActivity(),"数据获取失败", Toast.LENGTH_SHORT).show();
            }else {
                String s = msg.obj.toString();
                JSONArray array = null;
                try {
                    array = new JSONArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject object;
                Book book = new Book();
                List<Book> bookList = new ArrayList<Book>();
                for (int i = 0; i<array.length();i++){
                    try {
                        object = array.getJSONObject(i);
                        book.set_id(object.getInt("_id"));
                        book.setAuthor(object.getString("author"));
                        book.setBook_class_number(object.getString("book_class_name"));
                        book.setBook_name(object.getString("book_name"));
                        book.setContents(object.getString("contents"));
                        book.setPress(object.getString("press"));
                        book.setPress_date(object.getString("press_date"));
                        book.setReal_count(object.getInt("real_count"));
                        book.setTotal_count(object.getInt("total_count"));
                        bookList.add(book);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
                listView.setAdapter(new bookInfoAdapter(getActivity(),bookList));
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_select, null);
        final EditText txt = (EditText) view.findViewById(R.id.what_select);
        final Spinner spinner = (Spinner) view.findViewById(R.id.what_way);
        Button button = (Button) view.findViewById(R.id.btn_select);
        listView = (ListView) view.findViewById(R.id.list);
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_gallery_item, itemp));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BookSelect : ", "查找书籍");
                String str1 = txt.getText().toString();
                int position = spinner.getSelectedItemPosition();
                httpRequest http = new httpRequest();
                if (position == 0) {
                    http.selectByName(handler, str1);
                } else if (position == 1) {
                    http.selectByAuthor(handler, str1);
                } else {
                    http.selectByBookClassName(handler, str1);
                }

            }
        });
        return view;

    }
}
