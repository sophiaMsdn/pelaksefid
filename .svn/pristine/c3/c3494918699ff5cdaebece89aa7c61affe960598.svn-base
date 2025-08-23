/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.domain.enumaration;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Work
 */
public enum RoleEnm {

    SESSION(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(PermissionEnm.ADMIN));

    private final Set<PermissionEnm> permissions;

    RoleEnm(Set<PermissionEnm> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionEnm> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
