package com.example.attendancemonitoringsystemupes.activities.adapterclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.activities.Student;

import java.util.List;

public class AbsentStudentsAdapter extends RecyclerView.Adapter<AbsentStudentsAdapter.AbsentStudentViewHolder>
{
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
        holder.bind(absentStudents.get(position),position);
    }

    @Override
    public int getItemCount() {
        return absentStudents.size();
    }

    public class AbsentStudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        CheckBox tvStudentAttendanceStatus;

        public AbsentStudentViewHolder(View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.student_name);
            tvStudentAttendanceStatus=itemView.findViewById(R.id.absent_attendance_status);
        }

        public void bind(Student student,int pos)
        {
            tvStudentName.setText(student.getStudentName());
            tvStudentAttendanceStatus.setChecked(student.isAttendanceStatus());
            tvStudentAttendanceStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(this, absentStudents.get(pos).getStudentName() + " clicked!", Toast.LENGTH_SHORT).show();

                    if (absentStudents.get(pos).isAttendanceStatus()) {
                        absentStudents.get(pos).setAttendanceStatus(false);
                        tvStudentAttendanceStatus.setText("False");
                    } else {
                        absentStudents.get(pos).setAttendanceStatus(true);
                        tvStudentAttendanceStatus.setText("True");
                    }

                }
            });
            if(student.isAttendanceStatus()==true)
            tvStudentAttendanceStatus.setText("True");
            else
                tvStudentAttendanceStatus.setText("False");
        }
    }

}

