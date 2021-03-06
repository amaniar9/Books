package com.example.android.books;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakash on 3/29/2018.
 */

public final class Utils {

    private Utils(){


    }


    private static List<Book> extractBook(String bookJSon){

        if (TextUtils.isEmpty(bookJSon)){

            return null;
        }

        List<Book> bookies = new ArrayList<>();


        try {
            JSONObject root = new JSONObject(bookJSon);
            JSONArray bookArray = root.getJSONArray("items");

                for(int i = 0; i < bookArray.length(); i++){

                    JSONObject currBook = bookArray.getJSONObject(i);
                    JSONObject voluemInfo = currBook.getJSONObject("volumeInfo");
                    //JSONArray authors = voluemInfo.getJSONArray("authors");
                   // JSONObject author1 = authors.getJSONObject(0);

                    String title = voluemInfo.getString("title");
                    String author = voluemInfo.getString("authors");

                    Book nBook = new Book(title, author);
                    bookies.add(nBook);


                }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bookies;
    }

    /**
     * fetches info from url and creates list w/extractBook()
     */

    public static List<Book> fetchBookInfo(String url1){

        URL url = createUrl(url1);

        String jsonResponse ="";

        if(url != null){

            try{
                jsonResponse = makeHttpRequest(url);


            }catch (IOException e){


            }


        }

        List<Book> bookies = extractBook(jsonResponse);

        return bookies;

    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
               // Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
