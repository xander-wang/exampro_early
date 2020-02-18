package com.bjqf.service;

import com.bjqf.dao.StudentPaperDao;
import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;

import java.util.List;

public class StudentPaperService {
    StudentPaperDao studentPaperDao = new StudentPaperDao();

    public void addStudentPaper(StudentPaper studentPaper) {
        studentPaperDao.addStudentPaper(studentPaper);
    }

    public int score(int userid, String spid) {
        return studentPaperDao.score(userid, spid);
    }

    public List<StudentList> studentList(int userid) {
        return studentPaperDao.studentList(userid);
    }
}
