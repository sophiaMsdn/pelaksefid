/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.Rule;
import ir.aria.pelaksefid.domain.enumaration.RuleEnm;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface RuleRepository extends JpaRepository<Rule, Long> {

    public List<Rule> findByTypeAndIsActiveAndIsAlive(RuleEnm ruleEnm, Boolean TRUE, Boolean TRUE0);

    public List<Rule> findByTypeInAndIsActiveAndIsAlive(Set<RuleEnm> types, Boolean TRUE, Boolean TRUE0);

}
