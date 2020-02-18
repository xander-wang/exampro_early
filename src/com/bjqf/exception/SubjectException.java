package com.bjqf.exception;

import com.bjqf.entity.Subject;
import com.bjqf.entity.User;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class SubjectException extends Exception{
    public SubjectException(String msg) {
        super(msg);
    }

    public void addSubject(Subject subject) throws SubjectException {
        String sql = null;
        sql = "select * from subject where scontent = ?";
        List<User> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), subject.getScontent());
        if(list.size() == 0){
            sql = "insert into subject (scontent, sa, sb, sc, sd, skey, sstate) values (?, ?, ?, ?, ?, ?, ?)";
            JDBCUtil.executeUpdate(sql, subject.getScontent(), subject.getSa(), subject.getSb(), subject.getSc(), subject.getSd(), subject.getSkey(), subject.isSstate());
        } else {
            throw new SubjectException("题干已添加，不能重复添加");
        }
    }
}
