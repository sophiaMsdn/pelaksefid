/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1.model.response;

import ir.aria.pelaksefid.web.v1.model.response.base.BaseRes;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class LoginRes extends BaseRes {

    private String token;
    private String tp;
}
