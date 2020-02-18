package com.bjqf.util;

import java.util.List;

public class PageModel {
    private int pageNo;
    private int pageSize;
    private List dataList;
    private int count;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return count%pageSize == 0 ? count/pageSize : count/pageSize+1;
    }

    public int getPrePage() {
        if(this.pageNo <= 1) {
            return 1;
        } else {
            return this.pageNo - 1;
        }
    }

    public int getNextPage() {
        if(this.pageNo >= this.getTotalPage()){
            return this.getTotalPage();
        } else {
            return this.pageNo + 1;
        }
    }
}
