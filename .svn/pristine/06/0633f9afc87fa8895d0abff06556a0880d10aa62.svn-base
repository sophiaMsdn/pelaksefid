/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarBodyStatus;
import ir.aria.pelaksefid.domain.entity.base.Base;
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
public class BodyStatusDto extends BaseDto {

    private String id;
    private String description;
    private String type;

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(description)) {
            return false;
        }
        if (ValidationUtil.isEmpty(type)) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        CarBodyStatus ent = new CarBodyStatus();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarBodyStatus ent = (CarBodyStatus) e;
        ent.setDescription(description);
        ent.setType(type);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarBodyStatus ent = (CarBodyStatus) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        type = ent.getType();
    }
}
