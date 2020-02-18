package com.bjqf.dao;

import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class SubjectDao {
    public List<Subject> selectAll() {
        String sql = "select * from subject";
        return (List<Subject>) JDBCUtil.executeQuery(sql, new SubjectMapper(), null);
    }

    public int queryTotalNumber() {
        String sql = "select count(*) as count from subject";
        return Integer.parseInt(JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0).toString());
    }

    public List<Subject> queryByPage(int pageNo, int pageSize) {
        String sql = "select * from subject limit ?,?";
        return (List<Subject>) JDBCUtil.executeQuery(sql, new SubjectMapper(), (pageNo - 1) * pageSize, pageSize);
    }

    public void addSubject(Subject subject) {
        String sql = "insert into subject (scontent, sa, sb, sc, sd, skey, sstate) values (?, ?, ?, ?, ?, ?, ?)";
        JDBCUtil.executeUpdate(sql, subject.getScontent(), subject.getSa(), subject.getSb(), subject.getSc(), subject.getSd(), subject.getSkey(), subject.isSstate());
    }

    public List<Subject> selectBySid(int sid) {
        String sql = "select * from subject where sid = ?";
        return (List<Subject>) JDBCUtil.executeQuery(sql, new SubjectMapper(), sid);
    }

    public int updateSubject(Subject subject) throws SubjectException {
        String sql = "update subject set scontent=?, sa=?, sb=?, sc=?, sd=?, skey=?, sstate=? where sid=?";
        int num = JDBCUtil.executeUpdate(sql, subject.getScontent(), subject.getSa(), subject.getSb(), subject.getSc(), subject.getSd(), subject.getSkey(), subject.isSstate(), subject.getSid());
        return num;
    }
}
