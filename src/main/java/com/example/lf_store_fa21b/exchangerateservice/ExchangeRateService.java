package com.example.lf_store_fa21b.exchangerateservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    private RestTemplate restTemplate;

    public ExchangeRateService() {
        this.restTemplate = new RestTemplate();
    }

    private double getExchangeRate(String from, String to) {
        String url = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to;
        RateDto rateDto = this.restTemplate.getForObject(url, RateDto.class);
        return rateDto.getResult();
    }

    public RateDto convert(String from, String to, double amount) {
        double rate = getExchangeRate(from, to);
        double result = amount * rate;
        return new RateDto(result);

    }
}
