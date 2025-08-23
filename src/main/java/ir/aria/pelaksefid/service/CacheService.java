/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Cache;
import ir.aria.pelaksefid.domain.enumaration.CacheEnm;
import ir.aria.pelaksefid.domain.repository.CacheRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class CacheService extends BaseService {

    @Autowired
    private CacheRepository repository;

    public Cache controlAndSave(CacheEnm type, String data) {
        Cache oldCache = repository.findByTypeAndIsAliveAndIsActive(type, Boolean.TRUE, Boolean.TRUE);
        if (oldCache != null) {
            oldCache.setIsActive(Boolean.FALSE);
            repository.save(oldCache);
        }
        Cache cache = new Cache();
        cache.setType(type);
        cache.setCache(data);
        cache.setIsActive(Boolean.TRUE);
        cache.setIsAlive(Boolean.TRUE);
        return repository.save(cache);
    }
}
