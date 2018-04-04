package com.example.android.books;

/**
 * Created by Aakash on 3/29/2018.
 */

public class Book {
    String mTitle;
    String mAuthor;

    public  Book (String title, String author){

        mTitle = title;
        mAuthor = author;



    }



    public String getTitle(){

        return mTitle;
    }

    public String getAuthor(){

        return mAuthor;
    }
}
