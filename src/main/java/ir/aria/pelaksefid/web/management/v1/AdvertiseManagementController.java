/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.management.v1;

import ir.aria.pelaksefid.domain.entity.Advertise;
import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.domain.model.AdvertiseDto;
import ir.aria.pelaksefid.domain.query.model.AdvertiseModel;
import ir.aria.pelaksefid.service.AdvertiseService;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseReq;
import ir.aria.pelaksefid.web.v1.model.response.AdvertiseRes;
import ir.aria.pelaksefid.web.v1.model.response.AdvertisesRes;
import ir.aria.pelaksefid.web.v1.model.response.base.BaseRes;
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
@RequestMapping(path = "${mngapiv1}/advertises",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
public class AdvertiseManagementController {

    @Autowired
    private AdvertiseService service;

    @PostMapping(path = "/getPrivateAdvertises")
    public AdvertisesRes getPrivateAdvertises(@RequestBody AdvertiseFilterReq req,
            HttpServletRequest request) {

        AdvertisesRes res = new AdvertisesRes();

        Page<AdvertiseModel> advertises = service.getPrivateAdvertisesByFilter(req, request);

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

    @PostMapping(path = "/confirmAdvertise")
    public BaseRes insertAdvertise(@RequestBody AdvertiseReq req,
            HttpServletRequest request) {

        BaseRes res = new BaseRes();

        ResultEnm rslt = service.validateBeforeConfirm(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Advertise advertise = service.controlAndConfirm(req, request);

        if (advertise == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/deactiveAdvertise")
    public AdvertiseRes deactiveAdvertise(@RequestBody AdvertiseReq req,
            HttpServletRequest request) {

        AdvertiseRes res = new AdvertiseRes();

        ResultEnm rslt = service.validateBeforeDeactive(req);
        if (!rslt.equals(ResultEnm.OK)) {
            res.setStatus(rslt.ordinal());
            res.setMessage(rslt.name());
            return res;
        }

        Advertise advertise = service.controlAndDeactive(req, request);

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
}
