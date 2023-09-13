package com.userPreference.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Document
public class Wishlist {
    @Id
    private String userId;
    @Transient
    private String operation;
    private String wishListName;
    private List<String> listOfStocks;

    public Wishlist() {

    }











    public Wishlist(String userId, String wishListName, List<String> listOfStocks) {
    }
}
