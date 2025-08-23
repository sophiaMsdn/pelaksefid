/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client.consume.sigma;

import java.text.ParseException;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class SigmaToken {

    private String token;
    private Integer status;
    private Date expiration;

    public Boolean isExpired() throws ParseException {
        return !expiration.after(new Date());
    }

    public void setExpireDate() throws ParseException {
        expiration = new Date();
        expiration.setTime(expiration.getTime() + 10 * 60_000);//60_000
    }
}
