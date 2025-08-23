/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1;

import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.service.AccountService;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.AccountReq;
import ir.aria.pelaksefid.web.v1.model.response.LoginRes;
import ir.aria.pelaksefid.web.v1.model.response.base.BaseRes;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author work
 */
@RestController
@RequestMapping(path = "${apiv1}/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping(path = "/sendOtp")
    public BaseRes sendOtp(@RequestBody AccountReq req,
            HttpServletRequest request) {
        BaseRes res = new BaseRes();
        try {
            ResultEnm rslt = service.sendAccountOtpPass(req, request);
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        } catch (Throwable e) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(e.getMessage() + "6" + e.getLocalizedMessage() + "6" + e.toString() + "6" + e.getCause().getLocalizedMessage());
            return res;
        }
    }

    @PostMapping(path = "/confirmOtp")
    public LoginRes confirmOtp(@RequestBody AccountReq req,
            HttpServletRequest request
    ) {
        LoginRes res = new LoginRes();
        String token = service.loginByOtp(req, request);

        if (ValidationUtil.isEmpty(token)) {
            res.setStatus(ResultEnm.INVALID_PASSWORD.ordinal());
            res.setMessage(ResultEnm.INVALID_PASSWORD.name());
            return res;
        }

        res.setToken(token);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }
}
