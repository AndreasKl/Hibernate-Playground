package net.andreaskluth;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import net.andreaskluth.Stock.StockId;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        ListingRepository repo = ctx.getBean(ListingRepository.class);
        repo.save(createListingWithStock("a1", "s1", createStock("a1", "s1")));
        repo.save(createListingWithStock("a2", "s1", null));

        EntityManager em = ctx.getBean(EntityManager.class);
        em.clear();

        List<Listing> listings = repo.findAll();
        System.out.println(listings.get(0).getStock());
        System.out.println(listings.get(1).getStock());

    }

    private static Listing createListingWithStock(String articleId, String storeId, Stock stock) {
        Listing listing = new Listing();
        listing.setArticleId(articleId);
        listing.setStoreId(storeId);
        listing.setStock(stock);
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