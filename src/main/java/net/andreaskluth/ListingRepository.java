package net.andreaskluth;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @EntityGraph(attributePaths = { "stock" })
    List<Listing> findAll();

}
