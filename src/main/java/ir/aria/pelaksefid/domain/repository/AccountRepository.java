/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByCellNumber(String cellNumber);

    public Account findByCellNumberAndOtpPassword(String cellNumber, String password);

    public Object findByCellNumberAndIsActive(String username, Boolean TRUE);

    public Account findByIsMana(Boolean TRUE);

}
