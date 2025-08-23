/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarDifferential;
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
public class CarDifferentialDto extends BaseDto {

    private String id;
    private String description;
    private String persianName;

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(description)) {
            return false;
        }
        if (ValidationUtil.isEmpty(persianName)) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        CarDifferential ent = new CarDifferential();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarDifferential ent = (CarDifferential) e;
        ent.setDescription(description);
        ent.setPersianName(persianName);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarDifferential ent = (CarDifferential) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        persianName = ent.getPersianName();
    }
}
