package com.cfg.deploytools.model;

/**
 * ClassName: TableParse
 * Description:
 * date: 2020/6/8 14:42
 *
 * @author CFG
 * @since JDK 1.8
 */
public class TableParse {
    private int pageNum;

    private int pageSize;

    private String orderByColumn;

    private String isAsc;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
