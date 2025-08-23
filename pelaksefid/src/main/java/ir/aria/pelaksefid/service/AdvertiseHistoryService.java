/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Advertise;
import ir.aria.pelaksefid.domain.entity.AdvertiseHistory;
import ir.aria.pelaksefid.domain.entity.AdvertiseOperation;
import ir.aria.pelaksefid.domain.enumaration.AdvertiseOperationEnm;
import ir.aria.pelaksefid.domain.repository.AdvertiseHistoryRepository;
import ir.aria.pelaksefid.domain.repository.AdvertiseOperationRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class AdvertiseHistoryService extends BaseService {

    @Autowired
    private AdvertiseHistoryRepository repository;
    @Autowired
    private AdvertiseOperationRepository operationRepository;

    public void insertHistory(Advertise advertise,
            AdvertiseOperationEnm operationType,
            String username,
            String description,
            Long miscId,
            String date,
            String time,
            HttpServletRequest request) {
        AdvertiseHistory history = new AdvertiseHistory();
        history.setRegisterDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        history.setRegisterTime(sdf.format(cal.getTime()));
        history.setOperationDate(date);
        history.setOperationTime(time);
        history.setAdvertise(advertise);
        history.setUsername(username);
        history.setDescription(description);
        history.setMiscId(miscId);
        AdvertiseOperation operation = operationRepository.findByType(operationType);
        history.setOperation(operation);

        history = (AdvertiseHistory) logEntity(history, request);
        repository.save(history);
    }

    public List<AdvertiseHistory> getOrderHistories(Advertise advertise, AdvertiseOperationEnm operationType) {

        AdvertiseOperation operation = operationRepository.findByType(operationType);
        return repository.findByAdvertiseAndOperation(advertise, operation);
    }

}
