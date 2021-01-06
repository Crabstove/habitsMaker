package ca.georgebrown.comp3074.prototype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    public List<Habit> habitList;
    public OnItemClickListener mOnItemClickListener;

    public MyRecyclerViewAdapter(List<Habit> habitList, OnItemClickListener onItemClickListener) {
        this.habitList = habitList;
        mOnItemClickListener = onItemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_look, parent, false);
        return new MyViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.habitView.setText(habitList.get(position).getName());
        holder.buildOrQuit.setText(habitList.get(position).getType());
        holder.dayNumber.setText("DAY " + habitList.get(position).getDay_count());
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    //class ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView habitView;
        TextView buildOrQuit;
        TextView dayNumber;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            buildOrQuit = itemView.findViewById(R.id.buildOrQuit);
            habitView = itemView.findViewById(R.id.itemLook);
            dayNumber = itemView.findViewById(R.id.day_count);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
