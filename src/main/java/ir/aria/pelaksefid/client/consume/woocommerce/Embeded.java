/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client.consume.woocommerce;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Mana2
 */
public class Embeded {

    @SerializedName("wp:featuredmedia")
    private FeaturedMedia[] wpFeaturedmedia;

    public FeaturedMedia[] getWpFeaturedmedia() {
        return wpFeaturedmedia;
    }

    public void setWpFeaturedmedia(FeaturedMedia[] wpFeaturedmedia) {
        this.wpFeaturedmedia = wpFeaturedmedia;
    }
}
