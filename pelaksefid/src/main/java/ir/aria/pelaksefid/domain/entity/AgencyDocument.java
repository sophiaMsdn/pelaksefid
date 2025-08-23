/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.AgencyDocTypeEnm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_AGENCY_DOCUMENTS_SEQ", allocationSize = 1)
@Table(name = "PS_AGENCY_DOCUMENTS",
        indexes = {
            @Index(name = "IDX_PS_AGENCY_DOCS_001", columnList = "PS_AGENCY_ID"),
            @Index(name = "IDX_PS_AGENCY_DOCS_002", columnList = "AGENCY_DOC_TYPE"),})
public class AgencyDocument extends Base {

    @Column(name = "DOCUMENT_ID")
    private Long docId;
    @Column(name = "AGENCY_DOC_TYPE")
    @Enumerated(EnumType.STRING)
    private AgencyDocTypeEnm type;

    @ManyToOne
    @JoinColumn(name = "PS_AGENCY_ID")
    private Agency agency;
}
