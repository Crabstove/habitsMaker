package ca.georgebrown.comp3074.prototype2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class HabitListFragment extends Fragment implements MyRecyclerViewAdapter.OnItemClickListener {

    List<Habit> habitList = new ArrayList<>();
    DatabaseHandler dbHandler;

    public HabitListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DatabaseHandler(getContext(), DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        Cursor c = dbHandler.getAllHabits();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (c.moveToNext()) {
            try {
                habitList.add(new Habit(c.getString(1), c.getString(2), c.getInt(3), dateFormat.parse(c.getString(4))));
            }
            catch (ParseException e) {

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        //set adapter
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(habitList, this);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onItemClick(int position) {
        //Toast.makeText(getContext(),"you clicked "+habitList.get(position).toString() + " with id "+ position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("name", habitList.get(position).getName());
        intent.putExtra("day_count", String.valueOf(habitList.get(position).getDay_count()));
        intent.putExtra("id", position);
        intent.putExtra("lastDate", habitList.get(position).getLastDate().toString());
        startActivityForResult(intent, 1);
    }
}
