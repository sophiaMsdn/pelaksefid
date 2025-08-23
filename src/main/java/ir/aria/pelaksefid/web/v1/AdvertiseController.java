/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1;

import ir.aria.pelaksefid.domain.entity.Advertise;
import ir.aria.pelaksefid.domain.entity.AdvertiseDocument;
import ir.aria.pelaksefid.domain.entity.Document;
import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.domain.model.AdvertiseDto;
import ir.aria.pelaksefid.domain.model.DocumentDto;
import ir.aria.pelaksefid.domain.query.model.AdvertiseModel;
import ir.aria.pelaksefid.domain.repository.DocumentRepository;
import ir.aria.pelaksefid.service.AdvertiseService;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseReq;
import ir.aria.pelaksefid.web.v1.model.response.AdvertiseRes;
import ir.aria.pelaksefid.web.v1.model.response.AdvertisesRes;
import java.util.Optional;
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
@RequestMapping(path = "${apiv1}/advertises",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
public class AdvertiseController {

    @Autowired
    private AdvertiseService service;
    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping(path = "/insertAdvertise")
    public AdvertiseRes insertAdvertise(@RequestBody AdvertiseReq req,
            HttpServletRequest request) {

        AdvertiseRes res = new AdvertiseRes();

        ResultEnm rslt = service.validateBeforeSave(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Advertise advertise = service.controlAndSave(req, request);

        if (advertise == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AdvertiseDto advertiseDto = new AdvertiseDto();
        advertiseDto.fromEntity(advertise);
        res.setAdvertise(advertiseDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/updateAdvertise")
    public AdvertiseRes updateAdvertise(@RequestBody AdvertiseReq req,
            HttpServletRequest request) {

        AdvertiseRes res = new AdvertiseRes();

        ResultEnm rslt = service.validateBeforeUpdate(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Advertise advertise = service.controlAndUpdate(req, request);

        if (advertise == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AdvertiseDto advertiseDto = new AdvertiseDto();
        advertiseDto.fromEntity(advertise);
        res.setAdvertise(advertiseDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAdvertiseInfo")
    public AdvertiseRes getAdvertiseInfo(@RequestBody AdvertiseReq req,
            HttpServletRequest request) {

        AdvertiseRes res = new AdvertiseRes();

        Advertise advertise = service.getAdvertiseInfo(req, request);

        if (advertise == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        DocumentDto[] documentDtos = new DocumentDto[advertise.getAdvertiseDocuments().size()];
        int cntr = 0;
        AdvertiseDto advertiseDto = new AdvertiseDto();
        advertiseDto.fromEntity(advertise);
        for (AdvertiseDocument d : advertise.getAdvertiseDocuments()) {
            Optional<Document> document = documentRepository.findById(d.getDocId());
            if (document.isPresent()) {
                DocumentDto documentDto = new DocumentDto();
                documentDto.fromEntity(document.get());
                documentDtos[cntr++] = documentDto;
            }
        }
        advertiseDto.setDocuments(documentDtos);
        res.setAdvertise(advertiseDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAllAdvertises")
    public AdvertisesRes getAllAdvertises(@RequestBody AdvertiseFilterReq req,
            HttpServletRequest request) {

        AdvertisesRes res = new AdvertisesRes();

        Page<AdvertiseModel> advertises = service.getAllAdvertisesByFilter(req, request);

        if (advertises == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AdvertiseDto[] advertiseDtos = new AdvertiseDto[advertises.getNumberOfElements()];
        int cntr = 0;
        for (AdvertiseModel d : advertises) {
            AdvertiseDto advertiseDto = new AdvertiseDto();
            advertiseDto.fromModel(d);
            advertiseDtos[cntr++] = advertiseDto;
        }
        res.setCount(String.valueOf(advertises.getTotalElements()));
        res.setAdvertises(advertiseDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAccountAdvertises")
    public AdvertisesRes getAccountAdvertises(@RequestBody AdvertiseFilterReq req,
            HttpServletRequest request) {

        AdvertisesRes res = new AdvertisesRes();

        Page<AdvertiseModel> advertises = service.getAccountAdvertisesByFilter(req, request);

        if (advertises == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AdvertiseDto[] advertiseDtos = new AdvertiseDto[advertises.getNumberOfElements()];
        int cntr = 0;
        for (AdvertiseModel d : advertises) {
            AdvertiseDto advertiseDto = new AdvertiseDto();
            advertiseDto.fromModel(d);
            advertiseDtos[cntr++] = advertiseDto;
        }
        res.setCount(String.valueOf(advertises.getTotalElements()));
        res.setAdvertises(advertiseDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAllAgencyAdvertises")
    public AdvertisesRes getAllAgencyAdvertises(@RequestBody AdvertiseFilterReq req,
            HttpServletRequest request) {

        AdvertisesRes res = new AdvertisesRes();

        Page<AdvertiseModel> advertises = service.getAllAgencyAdvertisesByFilter(req, request);

        if (advertises == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        AdvertiseDto[] advertiseDtos = new AdvertiseDto[advertises.getNumberOfElements()];
        int cntr = 0;
        for (AdvertiseModel d : advertises) {
            AdvertiseDto advertiseDto = new AdvertiseDto();
            advertiseDto.fromModel(d);
            advertiseDtos[cntr++] = advertiseDto;
        }
        res.setCount(String.valueOf(advertises.getTotalElements()));
        res.setAdvertises(advertiseDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }
}
