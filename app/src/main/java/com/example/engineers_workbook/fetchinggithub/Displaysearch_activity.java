package com.example.engineers_workbook.fetchinggithub;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Displaysearch_activity extends AppCompatActivity {

    private static final String Sample_github_url ="https://api.github.com/search/repositories";
    private static final String param_q="q";
    private static final String param_sort="sort";
    private static String searchquery;
    private String sortby = "star";
    private EditText ed_querytaker;
    private TextView t_showresults;
    private Uri buildUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaysearch_activity);







    }


    private void Search(){

        ed_querytaker=(EditText) findViewById(R.id.querytaker);
        searchquery =ed_querytaker.getText().toString();


        buildUri =Uri.parse(Sample_github_url).buildUpon()
                .appendQueryParameter(param_q,searchquery)
                .appendQueryParameter(param_sort,sortby).build();

        
        t_showresults =(TextView) findViewById(R.id.repository_data);
        t_showresults.setText(buildUri.toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repository_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.search_button:
                //send data for search
                Search();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
