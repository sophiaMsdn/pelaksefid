/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.AdvertiseDocument;
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
public class AdvertiseDocumentDto extends BaseDto {

    private String id;
    private String advertiseId;
    private String docId;
    private String fileType;

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public Base toEntity() {
        AdvertiseDocument ent = new AdvertiseDocument();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        AdvertiseDocument ent = (AdvertiseDocument) e;
        ent.setIsActive(Boolean.TRUE);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        AdvertiseDocument ent = (AdvertiseDocument) e;
        id = String.valueOf(ent.getId());
        docId = ent.getId().toString();
    }
}
