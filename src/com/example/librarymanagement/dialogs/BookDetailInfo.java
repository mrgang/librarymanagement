package com.example.librarymanagement.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.librarymanagement.R;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.fragments.LoginState;
import com.example.librarymanagement.http.httpRequest;

/**
 * Created by ligan_000 on 2014/10/29.
 */
public class BookDetailInfo extends Dialog {
    private Context context;
    private Book book;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0)
                Toast.makeText(getContext(),"链接服务器出错"+msg.obj.toString(),Toast.LENGTH_SHORT).show();
            else if (msg.obj.toString().equals(true)){
                Toast.makeText(getContext(),"借阅成功",Toast.LENGTH_SHORT).show();
                dismiss();
            }

        }
    };

    public BookDetailInfo(Context context,Book book) {
        super(context);
        this.context = context;
        this.book = book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.book_detail_info);
        ((TextView)this.findViewById(R.id.book_name)).setText(book.getBook_name());
        ((TextView)this.findViewById(R.id.author_name)).setText(book.getAuthor());
        ((TextView)this.findViewById(R.id.book_class_number)).setText(book.getBook_class_number());
        ((TextView)this.findViewById(R.id.press)).setText(book.getPress());
        ((TextView)this.findViewById(R.id.press_date)).setText(book.getPress_date());
        ((TextView)this.findViewById(R.id.book_content)).setText(book.getContents());
        ((TextView)this.findViewById(R.id.txt_total_count)).setText(book.getTotal_count());
        ((TextView)this.findViewById(R.id.txt_real_count)).setText(book.getReal_count());

        ((TextView)this.findViewById(R.id.tv_lend)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpRequest http = new httpRequest();
                http.lendBook(handler,book.get_id(), LoginState.now_user);
            }
        });

    }
}
