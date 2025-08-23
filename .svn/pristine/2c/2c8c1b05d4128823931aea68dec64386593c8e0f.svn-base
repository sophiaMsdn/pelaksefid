/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.AgencyType;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AgencyTypeEnm;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import ir.aria.pelaksefid.utility.ValidationUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class AgencyTypeDto extends BaseDto {

    private String id;
    private String description;
    private String type;
    private String text;

    private String getTextType(String tp) {
        if (ValidationUtil.isEmpty(tp)) {
            return "";
        }
        if (AgencyTypeEnm.valueOf(tp).equals(AgencyTypeEnm.AGENCY)) {
            return "نمایندگی";
        }
        if (AgencyTypeEnm.valueOf(tp).equals(AgencyTypeEnm.SHOWROOM)) {
            return "نمايشگاه";
        }
        return "";
    }

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        AgencyType ent = new AgencyType();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        AgencyType ent = (AgencyType) e;
        ent.setDescription(description);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        AgencyType ent = (AgencyType) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        type = ent.getType().name();
        text = getTextType(ent.getType().name());
    }
}
