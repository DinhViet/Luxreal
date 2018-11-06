package com.truemoney.framework.persistence.dao;

public class PaginationInfo {
    private String orderColumn;
    private boolean orderAsc;
    private int firstRow;
    private int maxResults;

    public PaginationInfo() {
    }

    public PaginationInfo(String orderColumn, boolean orderAsc, int firstRow, int maxResults) {
	this.orderColumn = orderColumn;
	this.orderAsc = orderAsc;
	this.firstRow = firstRow;
	this.maxResults = maxResults;
    }

    public final String getOrderColumn() {
	return this.orderColumn;
    }

    public final void setOrderColumn(String orderColumn) {
	this.orderColumn = orderColumn;
    }

    public final boolean isOrderAsc() {
	return this.orderAsc;
    }

    public final void setOrderAsc(boolean orderAsc) {
	this.orderAsc = orderAsc;
    }

    public final int getFirstRow() {
	return this.firstRow;
    }

    public final void setFirstRow(int firstRow) {
	this.firstRow = firstRow;
    }

    public final int getMaxResults() {
	return this.maxResults;
    }

    public final void setMaxResults(int maxResults) {
	this.maxResults = maxResults;
    }
}