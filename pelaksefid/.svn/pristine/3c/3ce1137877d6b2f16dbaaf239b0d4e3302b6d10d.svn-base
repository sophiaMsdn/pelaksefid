/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.AgencyDocument;
import ir.aria.pelaksefid.domain.entity.Document;
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
public class AgencyDocumentDto extends BaseDto {

    private String id;
    private String docType;
    private DocumentDto document;

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(docType)) {
            return false;
        }
        if (document == null) {
            return false;
        }
        return true;
    }

    @Override
    public Base toEntity() {
        Document ent = new Document();
        return toEntity(ent);
    }

    @Override
    public Base toEntity(Base e) {
        AgencyDocument ent = (AgencyDocument) e;
        ent.setIsActive(Boolean.TRUE);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        AgencyDocument ent = (AgencyDocument) e;
        id = String.valueOf(ent.getId());
        docType = ent.getType().name();
    }
}
