/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.web.v1.model.response.base;

import ir.aria.pelaksefid.utility.MessageUtil;

/**
 *
 * @author Administrator
 */
public class BaseRes {

    private Integer status;
    private String message;
    private String persianMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.persianMessage = MessageUtil.getPersianMessage(message);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPersianMessage() {
        return persianMessage;
    }

    public void setPersianMessage(String persianMessage) {
        this.persianMessage = persianMessage;
    }
}
