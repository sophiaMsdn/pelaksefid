/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarTypeFuel;
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
public class CarTypeFuelDto extends BaseDto {

    private String id;
    private String fuelId;
    private String fuelDescription;
    private String carTypeId;
    private String carTypeDescription;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        CarTypeFuel ent = new CarTypeFuel();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarTypeFuel ent = (CarTypeFuel) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarTypeFuel ent = (CarTypeFuel) e;
        id = String.valueOf(ent.getId());
        fuelId = ent.getCarFuel().getId().toString();
        fuelDescription = ent.getCarFuel().getDescription();
        carTypeId = ent.getCarType().getId().toString();
        carTypeDescription = ent.getCarType().getDescription();
    }
}
