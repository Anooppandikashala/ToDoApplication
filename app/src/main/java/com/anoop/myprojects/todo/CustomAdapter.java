package com.anoop.myprojects.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.todo.DataModels.ToDoItem;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    ArrayList<ToDoItem> dataSet;



    public static  class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView title,date,time,id;
        ImageView delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.id= itemView.findViewById(R.id.id);
            this.title= itemView.findViewById(R.id.title);
            this.date= itemView.findViewById(R.id.date);
            this.time= itemView.findViewById(R.id.time);
            this.delete= itemView.findViewById(R.id.delete);

        }
    }



    public CustomAdapter(ArrayList<ToDoItem> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardlayout,
                parent,
                false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int position) {

        TextView title,date,time,id;
        ImageView delete;

        title = viewHolder.title;
        date = viewHolder.date;
        time = viewHolder.time;
        id = viewHolder.id;
        delete = viewHolder.delete;


        title.setText(dataSet.get(position).getTitle());
        date.setText(dataSet.get(position).getDate());
        time.setText(dataSet.get(position).getTime());
        id.setText(String.valueOf(dataSet.get(position).getId()));

        delete.setImageResource(R.drawable.ic_delete_forever_black_24dp);

        delete.setOnClickListener(MainActivity.deleteOnClickListner);


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
