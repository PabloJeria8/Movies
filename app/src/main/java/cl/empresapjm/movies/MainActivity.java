package cl.empresapjm.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.empresapjm.movies.models.Movie;

import static android.R.attr.button;
import static android.R.attr.name;
import static android.R.attr.onClick;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private List<Movie> moviesList;
    public static final String MOVIE_ID = "cl.empresapjm.movies.KEY.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText Edittextnamemovie = (EditText) findViewById(R.id.namemovieEt);
        Button buttonsave = (Button) findViewById(R.id.saveBtn);
        Button buttonlastmovie = (Button) findViewById(R.id.lastmovieBtn);

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namemovie = Edittextnamemovie.getText().toString();
                if (namemovie.trim().length() > 0) {
                    Movie movie = new Movie(namemovie, false);
                    movie.save();
                    moviesList = getMovies();
                    Edittextnamemovie.setText("");
                    Toast.makeText(MainActivity.this, "Película guardada.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Escriba un nombre por favor.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonlastmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = moviesList.size();
                if (size > 0) {
                    Movie lastMovie = moviesList.get(size-1);
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, lastMovie.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No hay películas.", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };
        buttonlastmovie.setOnClickListener(clickListener);*/

    }

    private List<Movie> getMovies(){
        return Movie.find(Movie.class, "watched = 0");
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesList = getMovies();
    }
}
