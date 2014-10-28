package com.example.librarymanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.librarymanagement.R;
import com.example.librarymanagement.entities.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligan_000 on 2014/10/23.
 */
public class bookInfoAdapter extends BaseAdapter{
    private List<Book> list;
    private LayoutInflater inflater;
    View myView = null;
    public bookInfoAdapter(Context context,List<Book> objects) {
        inflater = LayoutInflater.from(context);
        list = objects;
        myView = inflater.inflate(R.layout.book_info_item,null);
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
        if (myView == null){
            myView = inflater.inflate(R.layout.book_info_item,null);
        }
        view = myView;
        TextView book_name = (TextView)view.findViewById(R.id.book_name);
        TextView author = (TextView)view.findViewById(R.id.book_author);
        TextView total = (TextView)view.findViewById(R.id.book_total_count);
        TextView real = (TextView)view.findViewById(R.id.book_real_count);

        book_name.setText(list.get(i).getBook_name());
        author.setText(list.get(i).getAuthor());
        total.setText(list.get(i).getTotal_count());
        real.setText(list.get(i).getReal_count());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),view.getId()+"",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
