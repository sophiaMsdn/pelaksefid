/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.AgencyHistory;
import ir.aria.pelaksefid.domain.entity.AgencyOperation;
import ir.aria.pelaksefid.domain.enumaration.AgencyOperationEnm;
import ir.aria.pelaksefid.domain.repository.AgencyHistoryRepository;
import ir.aria.pelaksefid.domain.repository.AgencyOperationRepository;
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
public class AgencyHistoryService extends BaseService {

    @Autowired
    private AgencyHistoryRepository repository;
    @Autowired
    private AgencyOperationRepository operationRepository;

    public void insertHistory(Agency agency,
            AgencyOperationEnm operationType,
            String username,
            String description,
            Long miscId,
            String date,
            String time,
            HttpServletRequest request) {
        AgencyHistory history = new AgencyHistory();
        history.setRegisterDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        history.setRegisterTime(sdf.format(cal.getTime()));
        history.setOperationDate(date);
        history.setOperationTime(time);
        history.setAgency(agency);
        history.setUsername(username);
        history.setDescription(description);
        history.setMiscId(miscId);
        AgencyOperation operation = operationRepository.findByType(operationType);
        history.setOperation(operation);

        history = (AgencyHistory) logEntity(history, request);
        repository.save(history);
    }

    public List<AgencyHistory> getOrderHistories(Agency agency, AgencyOperationEnm operationType) {

        AgencyOperation operation = operationRepository.findByType(operationType);
        return repository.findByAgencyAndOperation(agency, operation);
    }

}
