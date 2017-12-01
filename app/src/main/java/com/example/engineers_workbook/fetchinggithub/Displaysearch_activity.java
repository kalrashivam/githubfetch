package com.example.engineers_workbook.fetchinggithub;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;



public class Displaysearch_activity extends AppCompatActivity {

    private static final String Sample_github_url ="https://api.github.com/search/repositories";
    private static final String param_q="q";
    private static final String param_sort="sort";
    private static String searchquery;
    private String sortby = "star";
    private EditText ed_querytaker;
    private TextView t_showresults;
    private Uri buildUri;
    private ProgressBar indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaysearch_activity);

        indicator = (ProgressBar) findViewById(R.id.pb);






    }

    public class GithubQuerytask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            indicator.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {

            String Response =null;
            try {
                Response = QueryUtils.getResponseFromHttp(urls[0]);
            }catch(IOException e){
                Log.e("not working", "http" +e);
            }
            return Response;
        }

        @Override
        protected void onPostExecute(String s) {
            indicator.setVisibility(View.INVISIBLE);
            if(s!=null && !s.isEmpty()){
                t_showresults =(TextView) findViewById(R.id.t_showresults);
                t_showresults.setText(s);
            }
        }
    }


    private void Search(){

        ed_querytaker=(EditText) findViewById(R.id.querytaker);
        searchquery =ed_querytaker.getText().toString();


        buildUri =Uri.parse(Sample_github_url).buildUpon()
                .appendQueryParameter(param_q,searchquery)
                .appendQueryParameter(param_sort,sortby).build();

        

    }

    private void GithubQuerysearch(){
        URL githubsearchurl = QueryUtils.CreateUrl(buildUri.toString());
        new GithubQuerytask().execute(githubsearchurl);

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
                GithubQuerysearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
