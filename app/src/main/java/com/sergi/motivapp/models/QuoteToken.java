package com.sergi.motivapp.models;

/**
 * Created by gersoft on 26/07/2017.
 */

public class QuoteToken {

    public String text, author;
    public int points;

    public QuoteToken(){

    }

    public QuoteToken(String text, String author){
        this.text = text;
        this.author = author;
    }

}
