/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mana2
 */
@Getter
@Setter
@Entity
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_DOCUMENTS_SEQ", allocationSize = 1)
@Table(name = "PS_DOCUMENTS")
public class Document extends Base {

    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Lob
    @Column(name = "CONTENT")
    private byte[] content;
}
