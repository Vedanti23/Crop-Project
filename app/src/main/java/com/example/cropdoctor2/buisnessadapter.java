package com.example.cropdoctor2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class buisnessadapter extends RecyclerView.Adapter<buisnessadapter.MyViewHolder> {

    private List<buisness> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc, link;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tit);
            desc= (TextView) view.findViewById(R.id.desc);
            link = (TextView) view.findViewById(R.id.year);
        }
    }


    public buisnessadapter(List<buisness> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buisness_row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        buisness movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.desc.setText(movie.getDesc());
        holder.link.setText(movie.getLink());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView link;

        public ViewHolder(View itemView) {

            super(itemView);

            title= (TextView) itemView.findViewById(R.id.tit);
            desc= (TextView) itemView.findViewById(R.id.desc);
            link=(TextView)itemView.findViewById(R.id.link);
        }
    }
}
