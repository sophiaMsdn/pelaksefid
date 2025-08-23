/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.Rule;
import ir.aria.pelaksefid.domain.enumaration.RuleEnm;
import ir.aria.pelaksefid.domain.repository.RuleRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class RuleService extends BaseService {

    @Autowired
    private RuleRepository repository;

    public List<Rule> getRules1() {
        return repository.findByTypeAndIsActiveAndIsAlive(RuleEnm.RULE1, Boolean.TRUE, Boolean.TRUE);
    }

    public List<Rule> getRules2() {
        return repository.findByTypeAndIsActiveAndIsAlive(RuleEnm.RULE2, Boolean.TRUE, Boolean.TRUE);
    }

    public List<Rule> getRules3() {
        return repository.findByTypeAndIsActiveAndIsAlive(RuleEnm.RULE3, Boolean.TRUE, Boolean.TRUE);
    }

    public List<Rule> getAboutUs() {
        return repository.findByTypeAndIsActiveAndIsAlive(RuleEnm.ABOUT_US, Boolean.TRUE, Boolean.TRUE);
    }

    public List<Rule> getContactUs() {
        return repository.findByTypeAndIsActiveAndIsAlive(RuleEnm.CONTACT_US, Boolean.TRUE, Boolean.TRUE);
    }
}
