package edu.uw.listviewdemo;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchField = (EditText)findViewById(R.id.txt_search);
        final ImageButton searchButton = (ImageButton)findViewById(R.id.btn_download);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Clicked!");
                downloadData();
            }
        });

        //model
//        String[] data = new String[99];
//        for(int i = 99; i > 0; i--){
//            data[99-i] = i + " bottles of beer on the wall";
//        }

        ArrayList<String> data = new ArrayList<String>();

        //view (done in list_item.xml)

        //controller
        this.adapter = new ArrayAdapter<String>(this, R.layout.list_item,
                R.id.txt_item, data);
        ListView list = (ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);

//        same thing but can also do grid view based on the xml
//        AdapterView list = (AdapterView)findViewById(R.id.list_view);
//        list.setAdapter(adapter);

    }

    private void downloadData(){
        String urlString = "http://dinoipsum.herokuapp.com/api?format=text&words=20&paragraphs=1";
        Request request = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        String[] dinos = response.split(" ");
                        adapter.clear(); //clear old data
                        for(String dino : dinos){
                            adapter.add(dino);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request); //this sends the request.

    }

    //LOOK AT COMPLETE BRANCH OF LECTURE 4 FOR JSON

    private void downloadJSON(){
        Request request = new JsonObjectRequest()
    }
}
