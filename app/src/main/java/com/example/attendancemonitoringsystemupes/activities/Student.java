package com.example.attendancemonitoringsystemupes.activities;

public class Student {
    private int studentId;
    private String studentName;
    private boolean attendanceStatus;

    public Student(int studentId, String studentName, boolean attendanceStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceStatus = attendanceStatus;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
