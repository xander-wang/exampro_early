package com.bjqf.service;

import com.bjqf.dao.FuntionDao;
import com.bjqf.dao.RoleDao;
import com.bjqf.entity.FunRole;
import com.bjqf.entity.Function;
import com.bjqf.entity.Role;
import com.bjqf.exception.FunctionException;
import com.bjqf.exception.RoleException;
import com.bjqf.util.PageModel;

import java.util.List;

public class FunctionService {
    FuntionDao funtionDao = new FuntionDao();

    public PageModel queryByPage(int pageNo, int pageSize) {
        PageModel pageModel = new PageModel();

        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        int count = funtionDao.queryTotalNumber();
        pageModel.setCount(count);
        List<Function> dataList = funtionDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(dataList);
        return pageModel;
    }

    public void addFunction(Function function) throws FunctionException {
        int num = funtionDao.addFunction(function);
        if(num == 0) {
            throw new FunctionException("功能名已添加，不可重复添加");
        }
    }

    public List<Function> selectByFunid(int funid) {
        return funtionDao.selectByFunid(funid);
    }

    public void updateFunction(Function function) throws FunctionException {
        int num = funtionDao.updateFunction(function);
        if(num == 0) {
            throw new FunctionException("该功能名已存在。");
        }
    }

    public List<FunRole> selectFunRole(int roleid) {
        return funtionDao.selectFunRole(roleid);
    }

}
