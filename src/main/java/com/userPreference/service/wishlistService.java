package com.userPreference.service;

import com.userPreference.constants.applicationConstants;

import com.userPreference.model.Wishlist;
import com.userPreference.repository.wishlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class wishlistService {

    private  static final Logger logger= LoggerFactory.getLogger(com.userPreference.service.wishlistService.class);

    @Autowired
    private wishlistRepository wishlistRepository;
    public Wishlist operationOnWishList(Wishlist wishlist){
        switch (wishlist.getOperation()) {
            case "create":
                logger.info("create wishlist method executed");
                return createWishlist(wishlist);
            case "update":
                logger.info("update wishlist method executed");
                return updateWishlist(wishlist);
            case "delete":
                logger.info("delete wishlist method executed");
                return deleteWishlist(wishlist);
            default:
                throw new IllegalArgumentException("Invalid operation: " + wishlist.getOperation());
        }   }

    private Wishlist deleteWishlist(Wishlist wishlist) {
        if (Boolean.TRUE.equals(wishlistRepository.findByUserId(wishlist.getUserId()==null))) {
            logger.error(applicationConstants.USER_NOT_FOUND);

            throw new IllegalArgumentException(applicationConstants.USER_NOT_FOUND);
        }
        logger.info("deleted the wishlist-"+wishlist.getWishListName());
        return wishlistRepository.deleteByWishListName(wishlist.getWishListName());
    }

    private Wishlist updateWishlist(Wishlist wishlist) {
        Wishlist list=new Wishlist();
        list.setUserId(wishlist.getUserId());
        list.setWishListName(wishlist.getWishListName());
        list.setListOfStocks(wishlist.getListOfStocks());

        logger.info("updated the wishlist-"+wishlist.getWishListName());
        return wishlistRepository.save(list);
    }
    private Map<String,Map<String,Wishlist>> userWishLists=new HashMap<>();
    public Wishlist createWishlist(Wishlist wishlist) {
        String userId=wishlist.getUserId();
        String wishListName=wishlist.getWishListName();
        if (userWishLists.containsKey(userId)){
            Map<String,Wishlist> existingWishList=userWishLists.get(userId);
            if (existingWishList.containsKey(wishListName)){
                throw new IllegalArgumentException("WishList Already Exists with this Name");
            }else {
                existingWishList.put(wishListName,wishlist);
            }
        }else {
            Map<String,Wishlist> newUserWishLists=new HashMap<>();
            newUserWishLists.put(wishListName,wishlist);
            userWishLists.put(userId,newUserWishLists);
        }
        return wishlistRepository.save(wishlist);

        }


}
