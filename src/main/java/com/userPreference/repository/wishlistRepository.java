package com.userPreference.repository;

import com.userPreference.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface wishlistRepository extends MongoRepository<Wishlist,String> {

    Wishlist deleteByWishListName(String wishListName);

   Boolean findByUserId(boolean userId);

    Wishlist findByWishListName(String wishListName);
}
