package net.andreaskluth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Stock {

    @Embeddable
    public static class StockId implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "article_id")
        private String articleId;

        @Column(name = "store_id")
        private String storeId;

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

    }

    @EmbeddedId
    private StockId id;

    private String value;

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
