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
import com.example.librarymanagement.fragments.LendHistory;
import com.example.librarymanagement.fragments.LoginState;
import com.example.librarymanagement.http.httpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ligan_000 on 2014/10/29.
 */
//用户书籍借阅历史信息的适配器。
public class bookListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> list;
    private LayoutInflater inflater;
    private boolean myflag = true;

    public bookListAdapter(Context context, ArrayList<HashMap<String, String>> list) {
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
        final String[] book_CN = new String[1];
        final httpRequest http = new httpRequest();
        final String lTime = list.get(i).get("lend_time"), rTime = list.get(i).get("return_time");
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
                    book_CN[0] = book.getBook_class_number();
                    myflag = false;
                }
            }
        }, list.get(i).get("book_class_name"));

        //借阅时间、应还时间提示，是否还书。
        View myview = View.inflate(context, R.layout.book_lend_time, null);

        ((TextView) myview.findViewById(R.id.tv_lend_time))
                .setText(lTime.substring(0, 4) + "年" + lTime.substring(4, 6) + "月" + lTime.substring(6, 8) + "日\t\t" + lTime.substring(8, 10) + "点");
        ((TextView) myview.findViewById(R.id.tv_return_time))
                .setText(rTime.substring(0, 4) + "年" + rTime.substring(4, 6) + "月" + rTime.substring(6, 8) + "日\t\t" + rTime.substring(8, 10) + "点");

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(myview)
                .setTitle("提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        http.returnBook(new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                if (msg.what == 0) {
                                    Toast.makeText(context, "连接服务器出错了" + msg.obj, Toast.LENGTH_SHORT).show();
                                } else if (msg.what == 1 && "false".equals(msg.obj.toString().trim())) {
                                    Toast.makeText(context, "还书失败，请稍后再试！", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "还书成功", Toast.LENGTH_SHORT).show();
                                    LendHistory.refresh.callOnClick();
                                }
                            }
                        }, LoginState.now_user, book_CN[0]);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        return view;
    }
}
