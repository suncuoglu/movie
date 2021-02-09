package com.suncuoglu.movieApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.suncuoglu.movieApp.R;
import com.suncuoglu.movieApp.adapter.MovieAdapter;
import com.suncuoglu.movieApp.model.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView posterImage;
    TextView titleText;
    TextView yearText;
    TextView typeText;
    String title;
    String year;
    String poster;
    String type;
    LinearLayout back_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        title = getIntent().getStringExtra("title");
        year = getIntent().getStringExtra("year");
        poster = getIntent().getStringExtra("poster");
        type = getIntent().getStringExtra("type");

        initView();
        setData();
        setClick();

    }

    void initView() {
        posterImage = findViewById(R.id.posterImage);
        titleText = findViewById(R.id.titleText);
        yearText = findViewById(R.id.yearText);
        typeText = findViewById(R.id.typeText);
        back_btn = findViewById(R.id.back_btn);
    }

    void setData() {
        Glide.with(this).load(poster).into(posterImage);
        yearText.setText(year);
        titleText.setText(title);
        typeText.setText(type.toUpperCase());
    }

    private void setClick(){
        back_btn.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_btn:
                   finish();
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
       finish();
    }
}
