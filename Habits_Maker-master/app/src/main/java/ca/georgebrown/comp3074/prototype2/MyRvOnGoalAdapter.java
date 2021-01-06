package ca.georgebrown.comp3074.prototype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyRvOnGoalAdapter extends RecyclerView.Adapter<MyRvOnGoalAdapter.MyViewHolderOnGoal> {
    List<Habit> habitList;

    public MyRvOnGoalAdapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public MyViewHolderOnGoal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_look,parent,false);
        return new MyViewHolderOnGoal(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderOnGoal holder, int position) {
        holder.progressBar.setMax(22);
        holder.progressBar.setProgress(habitList.get(position).getDay_count());
        holder.textView.setText(habitList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public class MyViewHolderOnGoal extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;
        public MyViewHolderOnGoal(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBarOnGoal);
            textView = itemView.findViewById(R.id.habit_name_on_goal);
        }
    }
}
