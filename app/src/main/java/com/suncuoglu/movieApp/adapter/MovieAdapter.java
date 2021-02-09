package com.suncuoglu.movieApp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.suncuoglu.movieApp.R;
import com.suncuoglu.movieApp.activities.MovieDetailActivity;
import com.suncuoglu.movieApp.model.Search;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Search> movieList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MovieAdapter(Activity activity, ArrayList<Search> movieList, Context context) {
        this.context=context;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_movie, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, final int position) {
        final Search movieData = movieList.get(position);
        try {
            final Activity activity = (Activity) context;

            holder.titleText.setText(movieData.getTitle());
            holder.yearText.setText(movieData.getYear().substring(0,4));
            holder.typeText.setText(movieData.getType().toUpperCase());

            Typeface roboto_black = ResourcesCompat.getFont(context, R.font.roboto_black);
            Typeface roboto = ResourcesCompat.getFont(context, R.font.roboto);
            holder.titleText.setTypeface(roboto_black);
            holder.yearText.setTypeface(roboto);
            holder.typeText.setTypeface(roboto);

            if (!movieData.getPoster().toString().contains("http")){
                Glide.with(context).load(R.drawable.default_img).into(holder.posterImage);
            }else {
                Glide.with(context).load(movieData.getPoster()).into(holder.posterImage);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("title",movieData.title);
                    intent.putExtra("poster",movieData.poster);
                    intent.putExtra("year",movieData.year);
                    intent.putExtra("type",movieData.type);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView posterImage;
        TextView titleText;
        TextView yearText;
        TextView typeText;

        public MyViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.poster_Image);
            titleText = itemView.findViewById(R.id.titleText);
            yearText = itemView.findViewById(R.id.yearText);
            typeText = itemView.findViewById(R.id.typeText);

        }
    }
}
