package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.OrderAPI;

import java.util.List;

/**
 * Created by duyle on 3/20/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderJSON {

    private OrderAPI order;
    private List<OrderAPI> list;

    public OrderAPI getOrder() {
        return order;
    }

    public void setOrder(OrderAPI order) {
        this.order = order;
    }

    public List<OrderAPI> getList() {
        return list;
    }

    public void setList(List<OrderAPI> list) {
        this.list = list;
    }
}
