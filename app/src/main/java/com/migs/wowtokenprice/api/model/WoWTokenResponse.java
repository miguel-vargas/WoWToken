package com.migs.wowtokenprice.api.model;

public class WoWTokenResponse {
    private int last_updated_timestamp;
    private int price;

    public void setLastUpdatedTimestamp(int last_updated_timestamp) {
        this.last_updated_timestamp = last_updated_timestamp;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
