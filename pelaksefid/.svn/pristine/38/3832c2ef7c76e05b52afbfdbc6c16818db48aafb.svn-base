/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.ManufactureYear;
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
public class ManufactureYearDto extends BaseDto {

    private String id;
    private String description;
    private String manufactureYearId;
    private String persianYear;
    private String miladiYear;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        ManufactureYear ent = new ManufactureYear();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        ManufactureYear ent = (ManufactureYear) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        ManufactureYear ent = (ManufactureYear) e;
        id = String.valueOf(ent.getId());
        manufactureYearId = ent.getId().toString();
        description = ent.getPersianYear().toString() + "-" + ent.getMiladiYear().toString();
        persianYear = ent.getPersianYear().toString();
        miladiYear = ent.getMiladiYear().toString();
    }
}
