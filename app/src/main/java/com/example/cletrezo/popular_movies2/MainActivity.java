package com.example.cletrezo.popular_movies2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

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
import java.util.ArrayList;
import java.util.Collections;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_POPULAR = 1;
    public static final int REQUEST_CODE_TOP_RATED = 2;
    String topRatedMovieUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=";
    final static String MOVIE_IN_CURRENT_CLICKED_POSITION = "theMovieInCurrentClickedPosition";
    int counter = 0;
    int movieIdOfMovieInCurrentlyClicked;
    public ArrayList<Movie> popularMoviesList = new ArrayList<>();
    private ArrayList<Movie> topRatedMoviesList = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    String movieVideoPath;
    String movieReviewPath;
    int moviePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = findViewById(R.id.gridview);
        MovieDataSource object = new MovieDataSource();


        // I got help with the below method from stackoverflow a question i asked
        //https://stackoverflow.com/questions/50553815/using-volley-to-make-2-separate-remote-requests-with-one-method
        OnResponseListener<ArrayList<Movie>> listener = new OnResponseListener<ArrayList<Movie>>() {
            ArrayList<String> videokeys= new ArrayList<>();
            @Override
            public void onSuccess(int tag, ArrayList<Movie> object) {
                if (tag == REQUEST_CODE_POPULAR) {
                    popularMoviesList = object; // initialize popularmovieslist
                    movies.addAll(object); //Add movies to movies
                    Log.i("number of movies added:", String.valueOf(popularMoviesList.size()));
                    counter++; // tracking control/number of times onSuccess in called

                } else if (tag == REQUEST_CODE_TOP_RATED) {
                    topRatedMoviesList = object;
                    movies.addAll(object);
                    Log.i("number of movied added:", String.valueOf(topRatedMoviesList.size()));
                    counter++; // tracking Onsuccess call by Listener
                }

                Collections.shuffle(movies); // popular + top rated movies----shuffle them. This is default display to the user when they first launch the app
                Log.i("counter=", String.valueOf(counter));
                if (counter == 2) { // Do not call the adapter and pass movies for display until background process finishes and movie contains both pop and toprated
                    Log.i("num of movies passed:", String.valueOf(movies.size()));
                    gridView.setAdapter(new MovieDisplayAdapter(MainActivity.this, movies));
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(MainActivity.this, "" + "Rated number:" + position, LENGTH_SHORT).show();
                            movieIdOfMovieInCurrentlyClicked = movies.get(position).getMovieid();
                            moviePosition=position;
                            movieVideoPath = "https://api.themoviedb.org/3/movie/" + movieIdOfMovieInCurrentlyClicked+ "/videos?api_key=" + MovieDataSource.API_KEY;
                            Log.v("movieId here :", String.valueOf(movieIdOfMovieInCurrentlyClicked));
                            //movieReviewPath = "https://api.themoviedb.org/3/movie/" + movieIdOfMovieInCurrentlyClicked + "/reviews?api_key=" + MovieDataSource.API_KEY;

                                    Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                                    //When clicked, the position of the current movie
                                    Movie movieIncurrentClickedPosition = movies.get(moviePosition);
                                    Log.v("moviePosition:",String.valueOf(moviePosition));
                                    intent.putExtra(MOVIE_IN_CURRENT_CLICKED_POSITION, movieIncurrentClickedPosition);

                                    startActivity(intent);




                                }







                    });
                }

            }

            @Override
            public void onError(Exception e) {
//            todo handle error
            }


        };
        object.movieArrayList(popularMoviesUrl, REQUEST_CODE_POPULAR, listener);
        object.movieArrayList(topRatedMovieUrl, REQUEST_CODE_TOP_RATED, listener);
        // Log.i(" of movies passed:", String.valueOf(movies.size()));


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popular) {
            GridView gridView = findViewById(R.id.gridview);
            getSupportActionBar().setTitle("Most Popular Movies");
            gridView.setAdapter(new MovieDisplayAdapter(this, popularMoviesList));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + "popularity, number:" + position, LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                    Movie movieIncurrentClickedPosition = popularMoviesList.get(position);
                    intent.putExtra(MOVIE_IN_CURRENT_CLICKED_POSITION,movieIncurrentClickedPosition);

                    startActivity(intent);

                }
            });

        } else if (item.getItemId() == R.id.toprated) {
            GridView gridView = findViewById(R.id.gridview);
            getSupportActionBar().setTitle("Top Rated Movies");
            gridView.setAdapter(new MovieDisplayAdapter(this, topRatedMoviesList));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(MainActivity.this, "" + "Rated number:" + position, LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                    Movie movieIncurrentClickedPosition=topRatedMoviesList.get(position);
                    intent.putExtra(MOVIE_IN_CURRENT_CLICKED_POSITION,movieIncurrentClickedPosition);
                    startActivity(intent);

                }
            });

        } else {


            return super.onOptionsItemSelected(item);
        }
        return true;

    }


}



