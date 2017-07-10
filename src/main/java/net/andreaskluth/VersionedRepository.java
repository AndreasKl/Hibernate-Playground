package net.andreaskluth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionedRepository extends JpaRepository<Versioned, String> {

}
