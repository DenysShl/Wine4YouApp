package com.sommelier.wine4you.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Role;
import com.sommelier.wine4you.repository.RoleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoleServiceTest {
    private static final String NonExistentRole = "USR";
    private static RoleRepository roleRepository;
    private static Role adminRole;

    @BeforeAll
    static void beforeAll() {
        roleRepository = Mockito.mock(RoleRepository.class);
        adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
    }

    @Test
    void save_validRoleDao_OK() {
        Mockito.when(roleRepository.save(adminRole)).thenReturn(adminRole);
        Role actual = roleRepository.save(adminRole);
        assertNotNull(actual);
        assertEquals(adminRole.getName(), actual.getName());
    }

    @Test
    void getRoleByName_nonExistent_notOk() {
        Mockito.when(roleRepository.findByName(NonExistentRole))
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,
                () -> roleRepository.findByName(NonExistentRole));
    }
}
