package com.bjqf.dao;

import com.bjqf.entity.FunRole;
import com.bjqf.entity.Function;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.FunRoleMapper;
import com.bjqf.mapper.FunctionMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class FuntionDao {

    public int queryTotalNumber() {
        String sql = "select count(*) as count from " +
                "(select a.*, (case when b.funname is null then '无' else b.funname end) " +
                "as funpname from function a left join function b on a.funpid=b.funid where 0=0) as fun";
        return (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
    }

    public List<Function> queryByPage(int pageNo, int pageSize) {
        String sql = "select a.*, (case when b.funname is null then '无' else b.funname end) " +
                "as funpname from function a left join function b on a.funpid=b.funid where 0=0 limit ?,?";
        return (List<Function>) JDBCUtil.executeQuery(sql, new FunctionMapper(), (pageNo-1)*pageSize);
    }

    public int addFunction(Function function) {
        String sql = "insert into function (funname, funurl, funpid, funstate) value (?,?,?,?)";
        return (int) JDBCUtil.executeUpdate(sql, function.getFunname(), function.getFunurl(), function.getFunpid(), function.isFunstate());
    }

    public List<Function> selectByFunid(int funid) {
        String sql = "select a.*, (case when b.funname is null then '无' else b.funname end) as funpname from function a left join function b on a.funpid=b.funid where 0=0 and a.funid = ?";
        return (List<Function>) JDBCUtil.executeQuery(sql, new FunctionMapper(), funid);
    }

    public int updateFunction(Function function) {
        String sql = "update function set funname=?, funurl=?, funstate=? where funid=?";
        return (int) JDBCUtil.executeUpdate(sql, function.getFunname(), function.getFunurl(), function.isFunstate(), function.getFunid());
    }

    public List<FunRole> selectFunRole(int roleid) {
        String sql = "select a.funid, a.funname, a.funurl, a.funpid, a.funstate, " +
                "(case when b.rrid is null then '0' else '1' end) " +
                "as rr from function a " +
                "left outer join permission b " +
                "on a.funid = b.funid and b.roleid=? " +
                "where a.funstate = 1";
        return (List<FunRole>) JDBCUtil.executeQuery(sql, new FunRoleMapper(), roleid);
    }
}
