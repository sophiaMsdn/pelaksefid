/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.AgencyDocument;
import ir.aria.pelaksefid.domain.repository.AgencyDocumentRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class AgencyDocumentService extends BaseService {

    @Autowired
    private AgencyDocumentRepository agencyDocumentRepository;

    public List<AgencyDocument> getDocumentsFromAgency(Agency agency) {
        return agencyDocumentRepository.findByIsActiveAndIsAliveAndAgency(Boolean.TRUE, Boolean.TRUE, agency);
    }
}
