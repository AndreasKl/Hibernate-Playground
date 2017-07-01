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
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

@Entity
public class Listing implements PersistentAttributeInterceptable {

    @Transient
    private PersistentAttributeInterceptor interceptor;

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "article_id", nullable = true)
    private String articleId;

    @Column(name = "store_id", nullable = true)
    private String storeId;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false, nullable = true),
            @JoinColumn(name = "store_id", referencedColumnName = "store_id", insertable = false, updatable = false, nullable = true) })
    @NotFound(action = NotFoundAction.IGNORE)
    @LazyToOne(LazyToOneOption.NO_PROXY)
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
        if (interceptor != null) {
            return (Stock) interceptor.readObject(this, "stock", stock);
        }
        return stock;
    }

    public void setStock(Stock stock) {
        if (interceptor != null) {
            this.stock = (Stock) interceptor.writeObject(this, "stock", this.stock, stock);
            return;
        }
        this.stock = stock;
    }

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return interceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {
        this.interceptor = interceptor;
    }

}
