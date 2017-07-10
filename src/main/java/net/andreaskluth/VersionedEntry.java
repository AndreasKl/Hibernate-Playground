package net.andreaskluth;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
public class VersionedEntry {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "versioned_id")
    private String versionedId;

    private String name;

    VersionedEntry() {

    }

    public VersionedEntry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
