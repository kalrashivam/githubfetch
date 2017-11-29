package com.example.engineers_workbook.fetchinggithub;

/**
 * Created by hp on 29-11-2017.
 */

public class Repository {

    private String name;

    private String html_url;

    private String owner;

    public Repository(String nname, String nhtml, String nowner){
        name = nname;
        html_url= nhtml;
        owner = nowner;
    }


    public String getName(){
        return name;
    }

    public  String getHtml_url(){
        return html_url;
    }

    public String getOwner(){
        return owner;
    }
}
