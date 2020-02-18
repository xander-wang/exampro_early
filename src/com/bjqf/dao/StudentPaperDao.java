package com.bjqf.dao;

import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.StudentListMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class StudentPaperDao {
    public void addStudentPaper(StudentPaper studentPaper) {
        String sql = "insert into studentpaper(spid, userid, sid, studentkey, studentstate, pname) values (?,?,?,?,?,?)";
        JDBCUtil.executeUpdate(sql, studentPaper.getSpid(), studentPaper.getUserid(), studentPaper.getSid(), studentPaper.getStudentkey(), studentPaper.getStudentstate(), studentPaper.getPname());
    }

    public int score(int userid, String spid) {
        String sql = "select count(*) as count from studentpaper where studentstate = 1 and studentpaper.userid = ? and spid = ?";
        return (int) JDBCUtil.executeQuery(sql, new CountMapper(), userid, spid).get(0);
    }

    public List<StudentList> studentList(int userid) {
        String sql = "select spid, userid, pname, count(if(studentstate=1, true, null)) as rightcount, count(if(studentstate=0, true, null)) as errorcount from studentpaper where userid = ? group by spid";
        return (List<StudentList>) JDBCUtil.executeQuery(sql, new StudentListMapper(), userid);
    }
}
