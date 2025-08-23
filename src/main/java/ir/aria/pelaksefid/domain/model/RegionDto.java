/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.Region;
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
public class RegionDto extends BaseDto {

    private String id;
    private String description;
    private String parentId;
    private String parentDescription;
    private String type;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        Region ent = new Region();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        Region ent = (Region) e;
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        Region ent = (Region) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        parentId = ent.getParent() != null ? ent.getParent().getId().toString() : "";
        parentDescription = ent.getParent() != null ? ent.getParent().getDescription() : "";
        type = ent.getType().name();
    }
}
