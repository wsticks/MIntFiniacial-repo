package com.williams.mint.mintfinancial.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardThirdPartyApiCountry {

    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private String latitude;
    private String longitude;
}
