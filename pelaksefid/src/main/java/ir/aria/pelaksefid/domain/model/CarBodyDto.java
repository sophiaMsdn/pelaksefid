/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarBody;
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
public class CarBodyDto extends BaseDto {

    private String id;
    private String description;
    private String persianName;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        CarBody ent = new CarBody();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarBody ent = (CarBody) e;
        ent.setIsAlive(Boolean.TRUE);
        ent.setDescription(description);
        ent.setPersianName(persianName);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarBody ent = (CarBody) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        persianName = ent.getPersianName();
    }
}
