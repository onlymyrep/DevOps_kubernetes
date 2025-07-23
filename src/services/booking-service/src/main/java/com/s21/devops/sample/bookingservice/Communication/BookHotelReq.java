package com.s21.devops.sample.bookingservice.model;

import javax.validation.constraints.NotBlank;

public class BookHotelReq {
    @NotBlank
    private String hotelUid;
    
    @NotBlank
    private String dateFrom;
    
    @NotBlank
    private String dateTo;

    // Геттеры и сеттеры
    public String getHotelUid() {
        return hotelUid;
    }

    public void setHotelUid(String hotelUid) {
        this.hotelUid = hotelUid;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}