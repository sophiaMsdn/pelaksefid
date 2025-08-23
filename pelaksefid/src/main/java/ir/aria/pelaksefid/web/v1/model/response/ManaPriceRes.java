/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1.model.response;

import ir.aria.pelaksefid.domain.model.ManaPriceDto;
import ir.aria.pelaksefid.web.v1.model.response.base.BaseListRes;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class ManaPriceRes extends BaseListRes {

    private ManaPriceDto[] manaPrices;
}
