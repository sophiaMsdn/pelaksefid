/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.entity;

import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.enumaration.CacheEnm;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Cacheable
@Entity
@SequenceGenerator(initialValue = 1, name = "idgenerator", sequenceName = "PS_CACHES_SEQ", allocationSize = 1)
@Table(name = "PS_CACHES")
public class Cache extends Base {

    @Column(name = "CACHE_TYPE")
    @Enumerated(EnumType.STRING)
    private CacheEnm type;
    @Lob
    @Column(name = "CACHE_DATA")
    private String cache;
}