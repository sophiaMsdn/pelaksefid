/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarTypeTrimColor;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class CarTypeTrimColorDto extends BaseDto {

    private String id;
    private String trimColorId;
    private String trimColorDescription;
    private String trimColorPersianName;
    private String carTypeId;
    private String carTypeDescription;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        CarTypeTrimColor ent = new CarTypeTrimColor();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarTypeTrimColor ent = (CarTypeTrimColor) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarTypeTrimColor ent = (CarTypeTrimColor) e;
        id = String.valueOf(ent.getId());
        trimColorId = ent.getCarTrimColor().getId().toString();
        trimColorDescription = ent.getCarTrimColor().getDescription();
        trimColorPersianName = ent.getCarTrimColor().getPersianName();
        carTypeId = ent.getCarType().getId().toString();
        carTypeDescription = ent.getCarType().getDescription();
    }
}
