package com.example.cletrezo.popular_movies2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity {
    final String ratingDenominator = "/10";
    static int movieId;

    //MovieExtension movieExtension;
    Movie movie;


    //static String movieVideoPath ="https://api.themoviedb.org/3/movie/260513/videos?api_key=499681bbe970c4022989fc7947723eb5&language=en-US";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_details);
        getSupportActionBar().setTitle("Movie Details");


       /* NestedScrollView item= (NestedScrollView) findViewById(R.id.nestedScrollView);
        View child = getLayoutInflater().inflate(R.layout., )
*/
        RecyclerView trailerRecyclerview;
        MovieTrailerRecyclerViewAdapter movieTrailerRecyclerViewAdapter;

        // Review recyclerview
        RecyclerView reviewRecyclerView;
        MovieReviewRecyclerViewAdapter movieReviewRecyclerViewAdapter;



        TextView movieTitleTextView = findViewById(R.id.movieTitle);
        ImageView movieImageView = findViewById(R.id.movieImage);
        TextView movieReleaseDateView = findViewById(R.id.movieReleaseDate);
        TextView movieRatingView = findViewById(R.id.movieRating);
        TextView movieDescriptionView = findViewById(R.id.movieDescription);




        movie = getIntent().getParcelableExtra(MainActivity.MOVIE_IN_CURRENT_CLICKED_POSITION);

        ArrayList<String>keys = getIntent().getStringArrayListExtra("data");
        Log.v("Videolis details:", String.valueOf(String.valueOf(keys).length()));

        //movieId = movie.getMovieid();
        Picasso.with(this)
                .load(movie.getMovieImagePath())
                .fit()
                .placeholder(R.drawable.progress_file)
                .error(R.drawable.ic_launcher_background)
                .into(movieImageView);

        movieTitleTextView.setText(movie.getMovieTitle());
        movieReleaseDateView.setText(movie.getMovieReleaseDate());
        movieRatingView.setText(String.format("%s%s", String.valueOf(movie.getMovieRating()), ratingDenominator));
        movieDescriptionView.setText(movie.getMovieDescripton());

        ArrayList<String>names = new ArrayList<>();
        names.add("luke");
        names.add("mike");
        names.add("Cole");
        names.add("omoge");
        names.add("oney");
        names.add("luke");
        names.add("mike");
        names.add("Cole");
        names.add("omoge");
        names.add("oney");
        names.add("luke");
        names.add("mike");
        names.add("Cole");
        names.add("omoge");
        names.add("oney");
        names.add("luke");
        names.add("mike");
        names.add("Cole");
        names.add("omoge");
        names.add("oney");
        names.add("luke");
        names.add("mike");
        names.add("Cole");
        names.add("omoge");
        names.add("oney");

        ArrayList<MovieExtension>list = new ArrayList<>();
        for(int i=0; i<30; i++){
            MovieExtension movieExtension = new MovieExtension("Mike", "how are you doing today?");
            list.add(movieExtension);

        }



        trailerRecyclerview = (RecyclerView)findViewById(R.id.trailers_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        trailerRecyclerview.setLayoutManager(linearLayoutManager);
        movieTrailerRecyclerViewAdapter = new MovieTrailerRecyclerViewAdapter(this,names);
        trailerRecyclerview.setAdapter(movieTrailerRecyclerViewAdapter);


        //
        reviewRecyclerView = findViewById(R.id.reviews_recyclerview);
        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(this);
        reviewRecyclerView.setLayoutManager(mlinearLayoutManager);
        movieReviewRecyclerViewAdapter = new MovieReviewRecyclerViewAdapter(this, list);
        reviewRecyclerView.setAdapter(movieReviewRecyclerViewAdapter);



    }


}