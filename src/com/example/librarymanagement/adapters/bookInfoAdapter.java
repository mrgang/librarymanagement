package com.example.librarymanagement.adapters;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.librarymanagement.fragments.LoginState;
import com.example.librarymanagement.http.httpRequest;
import java.util.ArrayList;

/**
 * Created by ligan_000 on 2014/10/23.
 */
public class bookInfoAdapter extends BaseAdapter {
    private ArrayList<Book> list;
    private LayoutInflater inflater;
    Context context;
    private Book book;
    private TextView book_name, author, total, real, content;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("借阅结果处理handler", msg.what + "  " + msg.obj.toString());
            if (msg.what == 0)
                Toast.makeText(context, "链接服务器出错" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
            else if (msg.obj.toString().trim().equals("true")) {
                Toast.makeText(context, "借阅成功", Toast.LENGTH_SHORT).show();
            }

        }
    };

    public bookInfoAdapter(Context context, ArrayList<Book> objects) {
        this.inflater = LayoutInflater.from(context);
        this.list = objects;
        this.context = context;
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

        view = inflater.inflate(R.layout.book_info_item, null);
        book = list.get(i);

        book_name = (TextView) view.findViewById(R.id.book_name);
        author = (TextView) view.findViewById(R.id.book_author);
        total = (TextView) view.findViewById(R.id.book_total_count);
        real = (TextView) view.findViewById(R.id.book_real_count);
        content = (TextView) view.findViewById(R.id.book_content);

        book_name.setText(book.getBook_name());
        author.setText(book.getAuthor());
        total.setText(book.getTotal_count() + "");
        real.setText(book.getReal_count() + "");
        content.setText(book.getContents());

        Log.i("借阅结果处理handler", i + " and "+book.get_id());
        View myDialogView = View.inflate(context, R.layout.book_detail_info, null);
        ((TextView) myDialogView.findViewById(R.id.book_name)).setText(book.getBook_name());
        ((TextView) myDialogView.findViewById(R.id.author_name)).setText(book.getAuthor());
        ((TextView) myDialogView.findViewById(R.id.book_class_number)).setText(book.getBook_class_number());
        ((TextView) myDialogView.findViewById(R.id.press)).setText(book.getPress());
        ((TextView) myDialogView.findViewById(R.id.press_date)).setText(book.getPress_date());
        ((TextView) myDialogView.findViewById(R.id.book_content)).setText(book.getContents());
        ((TextView) myDialogView.findViewById(R.id.txt_total_count)).setText(book.getTotal_count() + "");
        ((TextView) myDialogView.findViewById(R.id.txt_real_count)).setText(book.getReal_count() + "");

        ((TextView) myDialogView.findViewById(R.id.tv_lend)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpRequest http = new httpRequest();
                http.lendBook(handler, book.get_id(), LoginState.now_user);
            }
        });
        final AlertDialog dialog;
        dialog = new AlertDialog.Builder(context).setTitle("书籍详情").create();
        dialog.setView(myDialogView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        return view;
    }


}
