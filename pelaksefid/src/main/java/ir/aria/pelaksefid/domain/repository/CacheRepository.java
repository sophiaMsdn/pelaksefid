/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Cache;
import ir.aria.pelaksefid.domain.enumaration.CacheEnm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface CacheRepository extends JpaRepository<Cache, Long> {

    public Cache findByTypeAndIsAliveAndIsActive(CacheEnm cacheEnm, Boolean isAlive, Boolean isActive);
}
