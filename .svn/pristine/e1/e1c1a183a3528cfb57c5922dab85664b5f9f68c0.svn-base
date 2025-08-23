/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarTypeManufactureYear;
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
public class CarTypeManufactureYearDto extends BaseDto {

    private String id;
    private String manufactureYearId;
    private String persianYear;
    private String miladiYear;
    private String carTypeId;
    private String carTypeDescription;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        CarTypeManufactureYear ent = new CarTypeManufactureYear();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarTypeManufactureYear ent = (CarTypeManufactureYear) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarTypeManufactureYear ent = (CarTypeManufactureYear) e;
        id = String.valueOf(ent.getId());
        manufactureYearId = ent.getManufactureYear().getId().toString();
        persianYear = ent.getManufactureYear().getPersianYear().toString();
        miladiYear = ent.getManufactureYear().getMiladiYear().toString();
        carTypeId = ent.getCarType().getId().toString();
        carTypeDescription = ent.getCarType().getDescription();
    }
}
