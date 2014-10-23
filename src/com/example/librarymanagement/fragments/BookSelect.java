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
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class BookSelect extends Fragment {
    private static String[] itemp = {"书名","作者","分类号"};
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_select,null);
        final EditText txt = (EditText)view.findViewById(R.id.what_select);
        final Spinner spinner = (Spinner)view.findViewById(R.id.what_way);
        Button button = (Button)view.findViewById(R.id.btn_select);
        ListView listView = (ListView)view.findViewById(R.id.list);
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_gallery_item,itemp));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BookSelect : ","查找书籍");
                String str1 = txt.getText().toString();
                int position = spinner.getSelectedItemPosition();
                httpRequest http = new httpRequest();
                if (position == 0) {
                        http.selectByName(handler,str1);
                }
                else if (position == 1){
                        http.selectByAuthor(handler,str1);
                }
                else {
                        http.selectByBookClassName(handler,str1);
                }

            }
        });
        return view;

    }
}
