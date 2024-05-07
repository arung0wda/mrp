package org.example.demo.cl.client;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TemplateProvider {


    public RestTemplate customRestClient() {
        //Excluded customization for brevity
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.custom().build()));
    }
}
