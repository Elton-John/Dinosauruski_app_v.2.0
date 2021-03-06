package pl.dinosaurus.dinosauruski.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirstByNameIgnoringCase(String name);
}
