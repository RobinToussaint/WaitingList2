package com.example.waitinglist2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    MainActivity mainActivity;
    List<Student> studentList = new ArrayList<>();
    public StudentAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @NonNull @Override public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()) .inflate(R.layout.studenet_item, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, final int position) {
        final Student student = studentList.get(position);
        holder.name.setText(student.name + " (" + student.rollNumber + ")");
        holder.course.setText(student.course);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mainActivity.editCandidate(student, position);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mainActivity.deleteCandidate(student, position);
            }
        });
    }
    @Override public int getItemCount() {
        return studentList.size();
    }
    private static final String TAG = "StudentAdapter";
    public void setData(List<Student> studentList) {
        Log.i(TAG, "setData: "+studentList.size());
        this.studentList.clear();
        this.studentList .addAll(studentList);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, course;
        ImageView remove, edit;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_and_roll);
            course = view.findViewById(R.id.cource);
            remove = view.findViewById(R.id.remove);
            edit = view.findViewById(R.id.edit);
        }
    }
}
    /// modal file student package com.example.checkk;
    public class Student {
        String name;
        String rollNumber;
        String course;
        Integer priority;
        public Student(String name, String rollNumber, String course, Integer priority) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.course = course;
            this.priority = priority;
        }
        public String getName() {
            return name;
        }
        public String getRollNumber() {
            return rollNumber;
        }
        public String getCourse() {
            return course;
        }
        public Integer getPriority() {
            return priority;
        }
    }

