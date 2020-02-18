package com.bjqf.service;

import com.bjqf.dao.PaperErrorDao;
import com.bjqf.entity.PaperError;
import com.bjqf.util.PageModel;

import java.util.List;

public class PaperErrorService {
    PaperErrorDao paperErrorDao = new PaperErrorDao();

    public PageModel queryByPage(int userid, String spid, int pageNo, int pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        int count = paperErrorDao.queryTotalNumber(userid, spid);
        pageModel.setCount(count);

        List<PaperError> dataList = paperErrorDao.queryByPage(userid, spid, pageNo, pageSize);
        pageModel.setDataList(dataList);

        return pageModel;
    }
}
