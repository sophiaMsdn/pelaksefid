/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarTypeColor;
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
public class CarTypeColorDto extends BaseDto {

    private String id;
    private String colorId;
    private String colorDescription;
    private String colorPersianName;
    private String carTypeId;
    private String carTypeDescription;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        CarTypeColor ent = new CarTypeColor();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarTypeColor ent = (CarTypeColor) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarTypeColor ent = (CarTypeColor) e;
        id = String.valueOf(ent.getId());
        colorId = ent.getCarColor().getId().toString();
        colorDescription = ent.getCarColor().getDescription();
        colorPersianName = ent.getCarColor().getPersianName();
        carTypeId = ent.getCarType().getId().toString();
        carTypeDescription = ent.getCarType().getDescription();
    }
}
