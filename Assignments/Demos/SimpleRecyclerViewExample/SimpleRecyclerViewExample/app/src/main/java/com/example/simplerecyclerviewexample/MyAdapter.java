package com.example.simplerecyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public List<FootballTeam> teamList;

    public MyAdapter(List<FootballTeam> footballTeamList) {
        teamList = footballTeamList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, league, yearEstablished;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            league = (TextView) itemView.findViewById(R.id.tvLeague);
            yearEstablished = (TextView) itemView.findViewById(R.id.tvYear);

        }
    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.name.setText(teamList.get(position).getName());
        holder.league.setText(teamList.get(position).getLeague());
        holder.yearEstablished.setText(String.valueOf(teamList.get(position).getYearEstablished()));

    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
