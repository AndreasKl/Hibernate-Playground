package net.andreaskluth;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Id
    private String id = UUID.randomUUID().toString();

    @Embedded
    private StockId stockId;

    private String value;

    public StockId getId() {
        return stockId;
    }

    public void setId(StockId stockId) {
        this.stockId = stockId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
