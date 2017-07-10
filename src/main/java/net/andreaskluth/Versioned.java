package net.andreaskluth;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
// -> https://hibernate.atlassian.net/browse/HHH-10266
// @DynamicUpdate
// @DynamicInsert
public class Versioned {

    @Id
    private String id = UUID.randomUUID().toString();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Version
    private long version;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "versioned_id", referencedColumnName = "id")
    private Set<VersionedEntry> entries = new HashSet<>();

    Versioned() {
    }

    public Versioned(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public long getVersion() {
        return version;
    }

    public Set<VersionedEntry> entries() {
        return entries;
    }

    public void addEntry(VersionedEntry entry) {
        entries.add(entry);
    }

    public String getId() {
        return this.id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
