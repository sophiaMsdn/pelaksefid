/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.management.v1;

import ir.aria.pelaksefid.domain.entity.Agency;
import ir.aria.pelaksefid.domain.entity.AgencyDocument;
import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.domain.model.AgencyDocumentDto;
import ir.aria.pelaksefid.domain.model.AgencyDto;
import ir.aria.pelaksefid.domain.repository.AgencyDocumentRepository;
import ir.aria.pelaksefid.service.AgencyService;
import ir.aria.pelaksefid.web.v1.model.request.AgencyReq;
import ir.aria.pelaksefid.web.v1.model.response.AgenciesRes;
import ir.aria.pelaksefid.web.v1.model.response.base.BaseRes;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mana2
 */
@RestController
@RequestMapping(path = "${mngapiv1}/agencies",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
public class AgencyManagementController {

    @Autowired
    private AgencyService service;
    @Autowired
    private AgencyDocumentRepository agencyDocumentRepository;

    @PostMapping(path = "/getAgencies")
    public AgenciesRes getAgencies(@RequestBody AgencyReq req,
            HttpServletRequest request) {

        AgenciesRes res = new AgenciesRes();

        Page<Agency> agencies = service.getAgencies(req);

        if (agencies == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AgencyDto[] agencyDtos = new AgencyDto[agencies.getNumberOfElements()];
        int cntr = 0;
        for (Agency d : agencies) {
            int cntrd = 0;
            AgencyDocumentDto[] documents = new AgencyDocumentDto[d.getAgencyDocument().size()];
            AgencyDto agencyDto = new AgencyDto();
            agencyDto.fromEntity(d);
            List<AgencyDocument> docs = agencyDocumentRepository.findByAgencyAndIsActiveAndIsAlive(d, Boolean.TRUE, Boolean.TRUE);
            for (AgencyDocument ad : docs) {
                AgencyDocumentDto agencyDocument = new AgencyDocumentDto();
                agencyDocument.fromEntity(ad);
                agencyDocument.setId(ad.getDocId().toString());
                agencyDocument.setDocType(ad.getType().name());
                documents[cntrd++] = agencyDocument;
            }
            agencyDto.setDocuments(documents);
            agencyDtos[cntr++] = agencyDto;
        }
        res.setCount(String.valueOf(agencies.getTotalElements()));
        res.setAgencies(agencyDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/confirmAgency")
    public BaseRes insertAgency(@RequestBody AgencyReq req,
            HttpServletRequest request) {

        BaseRes res = new BaseRes();

        ResultEnm rslt = service.validateBeforeConfirm(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Agency agency = service.controlAndConfirm(req, request);

        if (agency == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/rejectAgency")
    public BaseRes rejectAgency(@RequestBody AgencyReq req,
            HttpServletRequest request) {

        BaseRes res = new BaseRes();

        ResultEnm rslt = service.validateBeforeReject(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Agency agency = service.controlAndReject(req, request);

        if (agency == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/deactiveAgency")
    public BaseRes deactiveAgency(@RequestBody AgencyReq req,
            HttpServletRequest request) {

        BaseRes res = new BaseRes();

        ResultEnm rslt = service.validateBeforeDeactive(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Agency agency = service.controlAndDeactive(req, request);

        if (agency == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }
}
