package com.example.canteen_management.Model;
public class Model {

    private String orderId, tableId, items;
    private boolean payment;

    public String getOrderId() { return this.orderId; }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItems() {
        return this.items;
    }
    public void setItems(String items) {
        this.items = items;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    public String getTableId() {
        return tableId;
    }

    public boolean isPayment() {
        return this.payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }


    public Model(String orderId, String tableId, String items, boolean payment) {
        this.tableId = tableId;
        this.orderId = orderId;
        this.items = items;
        this.payment = payment;
    }
}
