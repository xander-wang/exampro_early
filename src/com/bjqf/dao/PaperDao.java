package com.bjqf.dao;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.PaperMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class PaperDao {
    public List<Paper> selectAll() {
        String sql = "select paper.*, count(*) as pcount from paper group by pname";
        return (List<Paper>)JDBCUtil.executeQuery(sql, new PaperMapper(), null);
    }

    public int queryTotalNumber() {
        String sql = "select count(*) as count from (select paper.*, count(*) as pcount from paper group by pname) as p";
        return (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
    }

    public List<Paper> queryByPage(int pageNo, int pageSize) {
        String sql = "select * from ((select paper.*, count(*) as pcount from paper group by pname) as p) limit ?,?";
        return (List<Paper>) JDBCUtil.executeQuery(sql, new PaperMapper(), (pageNo-1)*pageSize, pageSize);
    }

    public void addPaper(String pname, int number) throws PaperException {
        String sql = null;
        sql = "select * from subject where sstate = 1";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), null);

        if(list.size() < number) {
            throw new PaperException("题目数大于题库数量，添加失败。");
        }else {
            sql = "insert into paper (pname, sid) select ?, sid from subject where sstate = 1 order by rand() limit ?";
            JDBCUtil.executeUpdate(sql, pname, number);
        }
    }

    public List<Subject> selectSub(String pname) {
        String sql = "select subject.* from subject " + "inner join paper " + "on paper.sid = subject.sid " + "where paper.pname = ?";
        return (List<Subject>) JDBCUtil.executeQuery(sql, new SubjectMapper(), pname);
    }
}
