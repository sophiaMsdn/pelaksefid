/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client.consume.woocommerce;

/**
 *
 * @author Mana2
 */
public class NewsRes {

    private NewRes[] news;
    private String count;

    public NewRes[] getNews() {
        return news;
    }

    public void setNews(NewRes[] news) {
        this.news = news;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
