package net.andreaskluth;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import net.andreaskluth.Stock.StockId;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        ListingRepository repo = ctx.getBean(ListingRepository.class);
        repo.save(createListingWithStock("a1", "s1", createStock("a1", "s1")));
        repo.save(createListingWithStock("a2", "s1", null));

        EntityManager em = ctx.getBean(EntityManager.class);
        em.clear();

        ListingService service = ctx.getBean(ListingService.class);
        service.doSth();
    }

    private static Listing createListingWithStock(String articleId, String storeId, Stock stock) {
        Listing listing = new Listing();
        listing.setArticleId(articleId);
        listing.setStoreId(storeId);
        listing.setStock(Optional.ofNullable(stock));
        return listing;
    }

    private static Stock createStock(String articleId, String storeId) {
        StockId stockId = new StockId();
        stockId.setArticleId(articleId);
        stockId.setStoreId(storeId);
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setValue("at least something has some value");
        return stock;
    }

}
