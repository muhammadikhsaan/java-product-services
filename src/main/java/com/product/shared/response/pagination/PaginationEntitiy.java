package com.product.shared.response.pagination;

import java.util.List;

public class PaginationEntitiy<T> {

    private Long count;

    private List<T> data;

    public PaginationEntitiy() {
    }

    public PaginationEntitiy(Long count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
