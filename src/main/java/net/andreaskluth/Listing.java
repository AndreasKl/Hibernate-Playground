package net.andreaskluth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Listing implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id private UUID id = UUID.randomUUID();

  @Column(name = "article_id", nullable = true)
  private String articleId;

  @Column(name = "store_id", nullable = true)
  private String storeId;

  /** To allow lazy loading mapped as @OneToMany instead of @OneToOne . */
  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  @JoinColumns(
      value = {
        @JoinColumn(
            name = "article_id",
            referencedColumnName = "article_id",
            insertable = false,
            updatable = false),
        @JoinColumn(
            name = "store_id",
            referencedColumnName = "store_id",
            insertable = false,
            updatable = false)
      })
  private Set<Stock> stock = new HashSet<>();

  @Version private long version = 0;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public Optional<Stock> getStock() {
    if (stock.isEmpty()) {
      return Optional.empty();
    }
    return stock
        .stream()
        .reduce(
            (t, u) -> {
              throw new IllegalStateException();
            });
  }

  public void setStock(Optional<Stock> stock) {
    this.stock.clear();
    stock.ifPresent(this.stock::add);
  }
}
