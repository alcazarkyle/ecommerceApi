package com.ws101.alcazar.ecommerceapi.initialization;

import com.ws101.alcazar.ecommerceapi.model.Role;
import com.ws101.alcazar.ecommerceapi.model.enums.RoleName;
import com.ws101.alcazar.ecommerceapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if ROLE_USER exists, if not, create it
        if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
            Role userRole = new Role(null, RoleName.ROLE_USER);
            roleRepository.save(userRole);
            System.out.println("Initialized ROLE_USER");
        }

        // Check if ROLE_ADMIN exists, if not, create it
        if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role(null, RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
            System.out.println("Initialized ROLE_ADMIN");
        }
    }
}