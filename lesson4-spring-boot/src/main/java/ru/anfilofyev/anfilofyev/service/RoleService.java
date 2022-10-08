package ru.anfilofyev.anfilofyev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anfilofyev.anfilofyev.model.Role;
import ru.anfilofyev.anfilofyev.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}