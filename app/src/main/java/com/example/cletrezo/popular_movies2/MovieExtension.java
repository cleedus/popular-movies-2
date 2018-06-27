package com.example.cletrezo.popular_movies2;

public class MovieExtension {

    private  String author;
    private String contents;



    public MovieExtension(String author, String contents) {

        this.author = author;
        this.contents = contents;
    }
    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }
}
