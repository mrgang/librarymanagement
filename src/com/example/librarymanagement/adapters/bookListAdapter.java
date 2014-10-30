package com.example.librarymanagement.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.librarymanagement.R;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.http.httpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ligan_000 on 2014/10/29.
 */
public class bookListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    private boolean myflag = true;

    public bookListAdapter(Context context, ArrayList<String> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.book_simple_info, null);
        final TextView book_name = (TextView) view.findViewById(R.id.book_name);
        final TextView author = (TextView) view.findViewById(R.id.book_author);
        final TextView contents = (TextView) view.findViewById(R.id.book_content);

        httpRequest http = new httpRequest();
        http.selectByBookClassName(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int flag = msg.what;
                Log.i("BookSelect : 获取的查询结果是 ", msg.obj.toString());
                if (flag == 0) {
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                } else {
                    String s = msg.obj.toString();
                    JSONArray array = null;
                    try {
                        array = new JSONArray(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject object;
                    Book book = new Book();
                    try {
                        object = array.getJSONObject(0);
                        book.set_id(object.getInt("_id"));
                        book.setAuthor(object.getString("author"));
                        book.setBook_class_number(object.getString("book_class_number"));
                        book.setBook_name(object.getString("book_name"));
                        book.setContents(object.getString("contents"));
                        book.setPress(object.getString("press"));
                        book.setPress_date(object.getString("press_date"));
                        book.setReal_count(object.getInt("real_count"));
                        book.setTotal_count(object.getInt("total_count"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    book_name.setText(book.getBook_name());
                    author.setText(book.getAuthor());
                    contents.setText(book.getContents());
                    myflag = false;
                }
            }
        }, list.get(i).toString());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialog = new AlertDialog.Builder(context)
                        .setMessage("你要还掉这本书吗?")
                        .setTitle("提示")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                dialog.show();
            }
        });
        return view;
    }
}
