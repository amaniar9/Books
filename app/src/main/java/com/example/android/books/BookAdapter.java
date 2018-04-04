package com.example.android.books;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakash on 3/29/2018.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books){
        super(context, 0, books);



    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        View listItem = convertView;

        if(listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Book currentBook =  getItem(pos);

        TextView titulo = (TextView) listItem.findViewById(R.id.title);
        titulo.setText(currentBook.getTitle());

        TextView authuro = (TextView) listItem.findViewById(R.id.author);
        authuro.setText(currentBook.getAuthor());



        return listItem;
    }
}
