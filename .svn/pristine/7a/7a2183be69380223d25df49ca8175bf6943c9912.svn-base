/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.domain.repository;

import ir.aria.pelaksefid.domain.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mana2
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsActive(String username, Boolean isActive);

    public long countByUsername(String username);

    public List<User> findByIdIn(long[] users);
}
