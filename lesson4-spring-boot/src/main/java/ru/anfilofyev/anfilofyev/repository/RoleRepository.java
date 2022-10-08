package ru.anfilofyev.anfilofyev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anfilofyev.anfilofyev.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}