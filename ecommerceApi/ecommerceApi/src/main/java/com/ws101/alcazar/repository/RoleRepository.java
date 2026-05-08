package com.ws101.alcazar.ecommerceapi.repository;

import com.ws101.alcazar.ecommerceapi.model.Role;
import com.ws101.alcazar.ecommerceapi.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
