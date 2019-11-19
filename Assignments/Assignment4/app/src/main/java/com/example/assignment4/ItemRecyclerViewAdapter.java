package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    public List<Character> posts;

    public ItemRecyclerViewAdapter(List<Character> posts){
        this.posts = posts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public ViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            image = itemView.findViewById(R.id.imageIV);

        }
    }// End of View Holder Class

    @Override
    public ItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_row, parent, false);
        return new ItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(posts.get(position).getName());
        holder.image.setImageDrawable(posts.get(position).getImageDrawable());
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }
}
