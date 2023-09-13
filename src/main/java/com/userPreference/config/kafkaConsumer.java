package com.userPreference.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userPreference.model.Wishlist;
import com.userPreference.service.wishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class kafkaConsumer {
    private  static final Logger logger= LoggerFactory.getLogger(com.userPreference.service.wishlistService.class);

    @Autowired
    private wishlistService wishlistService;
    private final ObjectMapper objectMapper;

    public kafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "test",groupId = "group-1")
    public  void  consumeMessage(String message) throws JsonProcessingException {
        Wishlist wishlist=objectMapper.readValue(message, Wishlist.class);
        String userId=wishlist.getUserId();
        String operation=wishlist.getOperation();
        String wishListName= wishlist.getWishListName();
        List<String> listOfStocks=wishlist.getListOfStocks();

        logger.info("Wishlist "+wishListName+" with userId :"+userId+" and operation performed :"+operation);
        wishlistService.operationOnWishList(wishlist);


}

}
