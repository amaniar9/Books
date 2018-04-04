package com.example.android.books;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String REQ_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=20";
    private BookAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookList = (ListView) findViewById(R.id.bookList);

        adapter = new BookAdapter(MainActivity.this, new ArrayList<Book>());

        bookList.setAdapter(adapter);

        new BookTask().execute(REQ_URL);





    }

    public class BookTask extends AsyncTask<String, Void, List<Book>>{




        @Override
        protected List<Book> doInBackground(String... strings) {

            List<Book> books = Utils.fetchBookInfo(strings[0]);
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> bookers){


            adapter.addAll(bookers);


        }
    }


}
