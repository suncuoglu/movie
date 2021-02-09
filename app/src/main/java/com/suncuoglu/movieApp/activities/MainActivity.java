package com.suncuoglu.movieApp.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.suncuoglu.movieApp.adapter.MovieAdapter;
import com.suncuoglu.movieApp.service.MovieService;
import com.suncuoglu.movieApp.R;
import com.suncuoglu.movieApp.model.MovieModel;
import com.suncuoglu.movieApp.model.Search;
import com.suncuoglu.movieApp.service.RestInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText movieName;
    Button button_search;
    RecyclerView recyclerView;
    TextView toolbar_text;
    TextView null_text;

    RestInterface restInterface;
    MovieAdapter movieAdapter;
    LinearLayoutManager linearLayoutManager;

    String title;
    LottieAnimationView animationView;


    ArrayList<Search> movieList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setFont();
        setClick();

        restInterface = MovieService.retrofit.create(RestInterface.class);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    private void initViews() {
        movieName = findViewById(R.id.text_movieName);
        button_search = findViewById(R.id.button_search);
        recyclerView = findViewById(R.id.recyclerView);
        animationView = findViewById(R.id.animationView);
        toolbar_text = findViewById(R.id.toolbar_title);
        null_text = findViewById(R.id.null_text);
    }
    private void setFont(){
        Typeface roboto_black = ResourcesCompat.getFont(getApplication(), R.font.roboto_black);
        button_search.setTypeface(roboto_black);
        toolbar_text.setTypeface(roboto_black);
    }
     private void setClick(){
         button_search.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_search:
                    title = movieName.getText().toString();
                    hideKeyboard();
                    if (!title.isEmpty()) {
                        animationView.playAnimation();
                        animationView.setVisibility(View.VISIBLE);

                        if (movieList!=null){
                            movieList.clear();
                        }

                        callRequestApi().enqueue(new Callback<MovieModel>() {
                            @Override
                            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                                try {
                                    animationView.pauseAnimation();
                                    animationView.setVisibility(View.GONE);
                                    movieList = response.body().getSearchArrayList();
                                    if (movieList!=null){
                                        movieAdapter = new MovieAdapter(MainActivity.this, movieList, MainActivity.this);
                                        recyclerView.setLayoutManager(linearLayoutManager);
                                        recyclerView.setAdapter(movieAdapter);
                                        null_text.setVisibility(View.GONE);
                                    }else {
                                        null_text.setVisibility(View.VISIBLE);
                                    }

                                }catch (Exception e){

                                }

                            }

                            @Override
                            public void onFailure(Call<MovieModel> call, Throwable t) {
                                //  Toast.makeText(MainActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
            }
        }
    };

    private retrofit2.Call<MovieModel> callRequestApi() {

        return restInterface.listMovie("feee8383", this.title);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}