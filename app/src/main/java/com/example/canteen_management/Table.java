package com.example.canteen_management;

public class Table {
    private String tableId;
    private boolean booked;

    Table() {
        this.tableId = null;
        this.booked = false;
    }
    Table(String tableId, boolean booked) {
        this.tableId = tableId;
        this.booked = booked;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
