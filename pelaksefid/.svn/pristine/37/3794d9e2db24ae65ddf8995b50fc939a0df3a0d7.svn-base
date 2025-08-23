/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Account;
import ir.aria.pelaksefid.domain.entity.Session;
import ir.aria.pelaksefid.domain.enumaration.AccountTypeEnm;
import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.domain.model.AccountDto;
import ir.aria.pelaksefid.domain.repository.AccountRepository;
import ir.aria.pelaksefid.domain.repository.SessionRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import ir.aria.pelaksefid.utility.SmsUtil;
import ir.aria.pelaksefid.utility.TokenUtil;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.AccountReq;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseReq;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class AccountService extends BaseService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private SessionRepository sessionRepository;

    public Account getAccountByBaseReq(BaseReq req) {

        if (!ValidationUtil.isBaseReqValid(req)) {
            return null;
        }

        String token = req.getToken();
        if (!ValidationUtil.isEmpty(token)) {
            Session session = sessionRepository.findByToken(token);
            if (session == null) {
                return null;
            }
            Account account = session.getAccount();
            return account;
        }
        return null;
    }

    @Transactional
    public ResultEnm sendAccountOtpPass(AccountReq req, HttpServletRequest request) {
        AccountDto a = req.getAccount();
        String cellNumber = a.getCellNumber();
        if (!ValidationUtil.isValidCellNumber(cellNumber)) {
            return ResultEnm.INVALID_ACCOUNT;
        }
        Integer otpCode = ThreadLocalRandom.current().nextInt(10000, 99999);
        Account account = repository.findByCellNumber(a.getCellNumber());
        if (account == null) {
            account = new Account();
            account.setCellNumber(cellNumber);
            account.setOtpPassword(otpCode.toString());
            account.setAccountType(AccountTypeEnm.ORD);
            account = (Account) logEntity(account, request);
            repository.save(account);
        } else {
            account.setOtpPassword(otpCode.toString());
            account = (Account) logEntity(account, request);
            repository.save(account);
        }

        SmsUtil.sendOtpCode(account.getCellNumber(), otpCode);
        return ResultEnm.OK;
    }

    @Transactional
    public String loginByOtp(AccountReq req, HttpServletRequest request) {
        AccountDto a = req.getAccount();
        String cellNumber = a.getCellNumber();
        String password = a.getOtpPassword();
        if (!ValidationUtil.isValidCellNumber(cellNumber) || ValidationUtil.isEmpty(password)) {
            return null;
        }
        Account account = repository.findByCellNumberAndOtpPassword(cellNumber, password);
        if (account == null) {
            return null;
        }
        String token = TokenUtil.getToken(account.getCellNumber());

        Session sessoin = new Session();
        sessoin.setToken(token);
        sessoin.setAccount(account);
        sessoin = (Session) logEntity(sessoin, request);
        sessionRepository.save(sessoin);

        if (account.getLoginCount() != null) {
            account.setLoginCount(account.getLoginCount() + 1);
        } else {
            account.setLoginCount(1L);
        }
        account = (Account) logEntity(account, request);
        repository.save(account);

        return token;
    }
}
