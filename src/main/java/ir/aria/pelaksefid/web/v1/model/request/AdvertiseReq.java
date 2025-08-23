/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1.model.request;

import ir.aria.pelaksefid.domain.model.AdvertiseDto;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseListReq;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter@Setter
public class AdvertiseReq extends BaseListReq {
    
    private AdvertiseDto advertise;
}
