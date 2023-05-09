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


public class PresentStudentsAdapter extends RecyclerView.Adapter<PresentStudentsAdapter.PresentStudentViewHolder> {
    private List<Student> presentStudents;

    public PresentStudentsAdapter(List<Student> presentStudents) {
        this.presentStudents = presentStudents;
    }

    @NonNull
    @Override
    public PresentStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_present_item, parent, false);
        return new PresentStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresentStudentViewHolder holder, int position) {
        holder.bind(presentStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return presentStudents.size();
    }

    public class PresentStudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvStudentAttendanceStatus;
        public PresentStudentViewHolder(View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.student_name);
            tvStudentAttendanceStatus=itemView.findViewById(R.id.present_attendance_status);
        }

        public void bind(Student student) {
            tvStudentName.setText(student.getStudentName());
            if(student.isAttendanceStatus()==true)
                tvStudentAttendanceStatus.setText("True");
            else
                tvStudentAttendanceStatus.setText("False");
        }
    }

}
