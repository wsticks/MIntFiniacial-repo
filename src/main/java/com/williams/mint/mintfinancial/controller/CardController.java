package com.williams.mint.mintfinancial.controller;

import com.williams.mint.mintfinancial.model.response.CardResponse;
import com.williams.mint.mintfinancial.service.CardService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/card-scheme")
public class CardController {

   private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @RequestMapping(path = "/verify/{bin}", produces = "application/json", method = RequestMethod.GET)
    public CardResponse getCardVerification(@PathVariable String bin) throws Exception{
        CardResponse cardResponse = cardService.cardVerification(bin);
        return cardResponse;
    }

}
