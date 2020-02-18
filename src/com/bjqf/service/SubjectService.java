package com.bjqf.service;

import com.bjqf.dao.SubjectDao;
import com.bjqf.entity.Subject;
import com.bjqf.entity.User;
import com.bjqf.exception.SubjectException;
import com.bjqf.util.PageModel;

import java.util.List;

public class SubjectService {
    SubjectDao subjectDao = new SubjectDao();

    public List<Subject> selectAll() {
        return subjectDao.selectAll();
    }

    public PageModel queryByPage(int pageNo, int pageSize) {
        PageModel pageModel = new PageModel();

        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        int count = subjectDao.queryTotalNumber();
        pageModel.setCount(count);

        List<Subject> list = subjectDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);

        return pageModel;
    }

    public void addSubject(Subject subject) throws SubjectException {
        subjectDao.addSubject(subject);
    }

    public List<Subject> selectBySid(int sid) {
        return subjectDao.selectBySid(sid);
    }

    public void updateSubject(Subject subject) throws SubjectException {
        int num = subjectDao.updateSubject(subject);
        if(num == 0) {
            throw new SubjectException("题干已存在");
        }
    }
}
