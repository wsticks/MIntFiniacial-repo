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
public class CardThirdPartyApiResponse {

    private String scheme;
    private String type;
    private String brand;
    private String prepaid;
    private CardThirdPartyApiCountry country;
    private CardThirdPartyBank bank;
    private CardThirdPartyNumber number;
}
