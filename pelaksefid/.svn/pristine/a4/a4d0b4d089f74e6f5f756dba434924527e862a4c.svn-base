/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Sms;
import ir.aria.pelaksefid.domain.enumaration.NotificationEnm;
import ir.aria.pelaksefid.domain.repository.SmsRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import ir.aria.pelaksefid.utility.DateFormatUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class SmsService extends BaseService {

    @Autowired
    private SmsRepository repository;

    public void insertSms(
            String cellNumber,
            String pattern,
            String data,
            NotificationEnm type,
            Boolean posted) {
        Sms sms = new Sms();
        sms.setCellNumber(cellNumber);
        sms.setPattern(pattern);
        sms.setData(data);
        sms.setType(type);
        sms.setPostageDate(new Date());
        sms.setExpireDate(DateFormatUtil.addDayToDate(new Date(), 3));
        sms.setTryCount(1);
        sms.setPosted(posted);
        sms.setIsActive(Boolean.TRUE);
        sms.setIsAlive(Boolean.TRUE);
        repository.save(sms);
    }

}
