package com.software.beacon;

import android.util.Log;

/**
 * Created by LENOVO on 14-03-2018.
 */

public class DealsOffersItem {
    private String description;
    private String original_price;
    private String imageURL;
    private String discounted_price;
    private String discount;
    private String validity;


    public DealsOffersItem(String head, String desc, String imageURL, String discounted, String discount, String validity) {
        this.description = head;
        this.original_price = desc;
        String[] bits = imageURL.split("/");
        Log.e("dopost",bits[bits.length-1]);
        this.imageURL = URLS.Local + bits[bits.length-1];
        this.discounted_price = discounted;
        this.discount = discount;
        this.validity = validity;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDiscount() {
        return discount;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public String getValidity() {
        return validity;
    }
}
