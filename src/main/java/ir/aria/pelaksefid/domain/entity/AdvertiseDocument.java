/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_ADVERTISE_DOCUMENTS_SEQ", allocationSize = 1)
@Table(name = "PS_ADVERTISE_DOCUMENTS",
        indexes = {
            @Index(name = "IDX_PS_ADVERTISE_DOCS_001", columnList = "PS_ADVERTISE_ID"),})
public class AdvertiseDocument extends Base {

    @Column(name = "DOCUMENT_ID")
    private Long docId;

    @ManyToOne
    @JoinColumn(name = "PS_ADVERTISE_ID")
    private Advertise advertise;
}
