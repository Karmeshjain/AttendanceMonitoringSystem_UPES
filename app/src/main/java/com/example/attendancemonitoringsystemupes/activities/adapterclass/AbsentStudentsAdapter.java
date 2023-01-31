package com.example.attendancemonitoringsystemupes.activities.adapterclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.activities.Student;

import java.util.List;

public class AbsentStudentsAdapter extends RecyclerView.Adapter<AbsentStudentsAdapter.AbsentStudentViewHolder> {
    private List<Student> absentStudents;

    public AbsentStudentsAdapter(List<Student> absentStudents) {
        this.absentStudents = absentStudents;
    }

    @NonNull
    @Override
    public AbsentStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_absent_item, parent, false);
        return new AbsentStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsentStudentViewHolder holder, int position) {
        holder.bind(absentStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return absentStudents.size();
    }

    public class AbsentStudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;

        public AbsentStudentViewHolder(View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.student_name);
        }

        public void bind(Student student) {
            tvStudentName.setText(student.getStudentName());
        }
    }

}

