package com.bjqf.service;

import com.bjqf.dao.PaperDao;
import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.util.PageModel;

import java.util.List;

public class PaperService {
    PaperDao paperDao = new PaperDao();

    public List<Paper> selectAll() {
        return paperDao.selectAll();
    }

    public PageModel queryByPage(int pageNo, int pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        int count = paperDao.queryTotalNumber();
        pageModel.setCount(count);

        List<Paper> list = paperDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }

    public void addPaper(String pname, int number) throws PaperException {
        paperDao.addPaper(pname, number);
    }

    public List<Subject> selectSub(String pname) {
        return paperDao.selectSub(pname);
    }
}
