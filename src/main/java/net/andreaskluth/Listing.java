package net.andreaskluth;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Listing {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "article_id", nullable = true)
    private String articleId;

    @Column(name = "store_id", nullable = true)
    private String storeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false, nullable = true),
            @JoinColumn(name = "store_id", referencedColumnName = "store_id", insertable = false, updatable = false, nullable = true) })
    private Stock stock;

    @Version
    private long version = 0;

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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
