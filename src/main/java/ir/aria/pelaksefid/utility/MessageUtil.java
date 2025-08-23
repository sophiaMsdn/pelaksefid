/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.utility;

import ir.aria.pelaksefid.domain.enumaration.ResultEnm;

/**
 *
 * @author Mana2
 */
public class MessageUtil {

    public static String getPersianMessage(String message) {
        if (message == null || message.equalsIgnoreCase("")) {
            return message;
        }
        if (message.equalsIgnoreCase(ResultEnm.OK.name())) {
            return "عملیات موفقیت آمیز!";
        }
        if (message.equalsIgnoreCase(ResultEnm.INVALID_INPUT.name())) {
            return "ورودی نامعتبر!";
        }
        if (message.equalsIgnoreCase(ResultEnm.INVALID_ACCOUNT.name())) {
            return "حساب کاربری نامعتبر!";
        }
        if (message.equalsIgnoreCase(ResultEnm.INVALID_PASSWORD.name())) {
            return "رمز نامعتبر!";
        }
        return message;
    }
}
