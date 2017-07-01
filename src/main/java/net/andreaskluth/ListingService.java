package net.andreaskluth;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ListingService {

    private ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Transactional
    public void doSth() {
        List<Listing> listings = listingRepository.findAll();
        System.out.println(listings.get(0).getStock());
        System.out.println(listings.get(1).getStock());
    }

}
