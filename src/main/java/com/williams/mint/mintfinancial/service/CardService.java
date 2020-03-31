package com.williams.mint.mintfinancial.service;

import com.williams.mint.mintfinancial.model.entity.Card;
import com.williams.mint.mintfinancial.model.response.CardResponse;
import com.williams.mint.mintfinancial.model.response.CardThirdPartyApiResponse;
import com.williams.mint.mintfinancial.model.response.Payload;
import com.williams.mint.mintfinancial.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private  CardRepository cardRepository ;
     RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://lookup.binlist.net/";
    private static final Integer DEFAULT_COUNT = 1;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    public CardService() {
    }



    public CardService(CardRepository cardRepository){

        this.cardRepository = cardRepository;
    }



    public CardResponse cardVerification(
            String bin) throws Exception{
        String url = BASE_URL + bin;
        System.out.println("url" + url);
        CardThirdPartyApiResponse cardThirdPartyApiResponse =
                restTemplate.getForObject(url,CardThirdPartyApiResponse.class);
        System.out.println("response" + cardThirdPartyApiResponse);
         CardResponse cardResponse = castToCardApiResponse(cardThirdPartyApiResponse);
        saveResponse(cardResponse, bin);
        kafkaTemplate.send("com.ng.vela.even.card_verified", String.valueOf(cardResponse));
        return cardResponse;
    }

    public  CardResponse castToCardApiResponse(CardThirdPartyApiResponse cardThirdPartyApiResponse){
        CardResponse cardResponse = new CardResponse();
        cardResponse.setSuccess("true");
        cardResponse.setPaylaod(castToCardApiResponseForPayLoad(cardThirdPartyApiResponse));
//        kafkaTemplate.send("Payment-From-Card", String.valueOf(cardResponse));
        return cardResponse;
    }

    public Payload castToCardApiResponseForPayLoad(CardThirdPartyApiResponse cardThirdPartyApiResponse){
        Payload payload = new Payload();
        payload.setBank(cardThirdPartyApiResponse.getBank().getName());
        payload.setScheme(cardThirdPartyApiResponse.getScheme());
        payload.setType(cardThirdPartyApiResponse.getType());
        return payload;
    }


    public void saveResponse(CardResponse cardResponse,  String bin){
        System.out.println("Check if bin came here" + bin);
        List<Card> fetchCards = cardRepository.findAll();
        Card filteredCard = fetchCards.stream()
                .filter(fetchCard-> fetchCard.getBin().equals(bin))
                .findAny()
                .orElse(null);
        if(filteredCard == null){
            Card cardToSave = new Card();
            cardToSave.setBank(cardResponse.getPaylaod().getBank());
            cardToSave.setScheme(cardResponse.getPaylaod().getScheme());
            cardToSave.setType(cardResponse.getPaylaod().getType());
            cardToSave.setCount(String.valueOf(DEFAULT_COUNT));
            cardToSave.setBin(bin);
            cardRepository.save(cardToSave);
        }else {
            String existingCountValue = filteredCard.getCount();
            Integer newCount = Integer.parseInt(existingCountValue) + 1;
            String newCountValue = String.valueOf(newCount);
            filteredCard.setCount(newCountValue);
            cardRepository.save(filteredCard);
        }
    }


    }
