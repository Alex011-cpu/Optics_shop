package com.example.optics.repository;

import com.example.optics.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для ролей
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
