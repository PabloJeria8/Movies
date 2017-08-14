package cl.empresapjm.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import cl.empresapjm.movies.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private CheckBox viewCk;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        viewCk = (CheckBox) findViewById(R.id.movieviewCb);

        long id = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);

        movie = Movie.findById(Movie.class, id);

        getSupportActionBar().setTitle(movie.getName());

        Log.d("IDPrueba", String.valueOf(id));
    }

    @Override
    protected void onPause() {
        super.onPause();

        movie.setWatched(viewCk.isChecked());
        movie.save();
    }



}
