/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.model;

import ir.aria.pelaksefid.domain.entity.Document;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.model.base.BaseDto;
import ir.aria.pelaksefid.utility.ValidationUtil;
import javax.xml.bind.DatatypeConverter;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
public class DocumentDto extends BaseDto {

    private String id;
    private String content;
    private String fileName;
    private String fileType;
    private String documentType;
    private String docId;

    @Override
    public Boolean isValid() {
        if (ValidationUtil.isEmpty(content)) {
            return false;
        }
        if (ValidationUtil.isEmpty(fileType)) {
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
        Document ent = (Document) e;
        byte[] contentArray = DatatypeConverter.parseBase64Binary(content.split(",")[1]);
        ent.setContent(contentArray);
        ent.setFileName(fileName);
        ent.setFileType(fileType);
        ent.setIsActive(Boolean.TRUE);
        ent.setIsAlive(Boolean.TRUE);
        return ent;
    }

    @Override
    public void fromEntity(Base e) {
        Document ent = (Document) e;
        id = String.valueOf(ent.getId());
        content = DatatypeConverter.printBase64Binary(ent.getContent());
        fileName = ent.getFileName();
        fileType = ent.getFileType();
    }
}
