/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.domain.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.aria.pelaksefid.domain.entity.base.Base;

/**
 *
 * @author Administrator
 */
abstract public class BaseDto {

    @JsonIgnore
    abstract public Boolean isValid();

    abstract public Base toEntity();

    abstract public Base toEntity(Base e);
    
    abstract public void fromEntity(Base e);
}
