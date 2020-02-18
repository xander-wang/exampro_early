package com.bjqf.dao;

import com.bjqf.entity.PaperError;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.PaperErrorMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class PaperErrorDao {
    public int queryTotalNumber(int userid, String spid) {
        String sql = "select count(*) as count from " + "(select s.sid, s.scontent, s.sa, s.sb, s.sc, s.sd, studentpaper.studentkey from studentpaper " +
                "inner join subject as s " + "on studentpaper.sid = s.sid " + "where studentstate = 0 and studentpaper.userid = ? and spid = ?) as p";
        return (int) JDBCUtil.executeQuery(sql, new CountMapper(), userid, spid).get(0);
    }

    public List<PaperError> queryByPage(int userid, String spid, int pageNo, int pageSize) {
        String sql = "select s.sid,s.scontent,s.sa,s.sb,s.sc,s.sd,s.skey,studentpaper.studentkey from studentpaper "
                + "inner join subject as s "
                + "on studentpaper.sid = s.sid "
                + "where studentstate = 0 and studentpaper.userid = ? and spid = ? "
                + "limit ?,?";
        return (List<PaperError>) JDBCUtil.executeQuery(sql, new PaperErrorMapper(), userid, spid, (pageNo-1) * pageSize, pageSize);
    }
}
