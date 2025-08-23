/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.Rule;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import ir.aria.pelaksefid.utility.ValidationUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 2
 */
@Getter
@Setter
public class RuleDto extends BaseDto {

    private String id;
    private String description;
    private String type;
    private String isActive;

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(description)) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        Rule ent = new Rule();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        Rule ent = (Rule) e;
        ent.setDescription(description);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        Rule ent = (Rule) e;
        id = String.valueOf(ent.getId());
        description = ent.getDescription();
        type = ent.getType() != null ? ent.getType().name() : "";
        isActive = ent.getIsActive() != null && ent.getIsActive() ? "1" : "0";
    }
}
