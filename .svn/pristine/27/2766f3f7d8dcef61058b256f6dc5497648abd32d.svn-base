/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.CarType;
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
public class CarTypeDto extends BaseDto {

    private String id;
    private String description;
    private String persianName;
    private String brandId;
    private String brandDescription;
    private String carModelId;
    private String carModelDescription;
    private String carDifferentialId;
    private String carDifferentialDescription;
    private String carcarCylinderId;
    private String carCylinderDescription;
    private String carGearId;
    private String carGearDescription;
    private String carBodyId;
    private String carBodyDescription;
    private String carEngineId;
    private String carEngineDescription;
    private String carOriginId;
    private String carOriginDescription;
    private CarTypeFuelDto[] carTypeFuels;
    private CarTypeColorDto[] carTypeColors;
    private CarTypeTrimColorDto[] carTypeTrimColors;
    private CarTypeManufactureYearDto[] carTypeManufactureYears;

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
        CarType ent = new CarType();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        CarType ent = (CarType) e;
        ent.setDescription(description);
        ent.setPersianName(persianName);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        CarType ent = (CarType) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        persianName = ent.getPersianName();
        carModelId = ent.getCarModel().getId().toString();
        carModelDescription = ent.getCarModel().getDescription();
        brandId = ent.getCarModel().getBrand().getId().toString();
        brandDescription = ent.getCarModel().getBrand().getDescription();
        carDifferentialId = ent.getCarDifferential().getId().toString();
        carDifferentialDescription = ent.getCarDifferential().getDescription();
        carcarCylinderId = ent.getCarCylinder().getId().toString();
        carCylinderDescription = ent.getCarCylinder().getDescription();
        carGearId = ent.getCarGear().getId().toString();
        carGearDescription = ent.getCarGear().getDescription();
        carBodyId = ent.getCarBody().getId().toString();
        carBodyDescription = ent.getCarBody().getDescription();
        carEngineId = ent.getCarEngine().getId().toString();
        carEngineDescription = ent.getCarEngine().getDescription();
        carOriginId = ent.getCarOrigin().getId().toString();
        carOriginDescription = ent.getCarOrigin().getDescription();
    }
}
