package org.example.demo.cl;

import org.example.demo.cl.client.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ContentLengthApplication {

    public static void main(String[] args) {

        Logger log = LoggerFactory.getLogger("MainApplication");


        ConfigurableApplicationContext ctx = SpringApplication.run(ContentLengthApplication.class, args);

        RestTemplate template = ctx.getBean(TemplateProvider.class).customRestClient();

        log.info("\n\n\n\nWithout body:\n\n\n\n\n");
        try {
            HttpEntity<?> reqEntity = new HttpEntity<>(new HttpHeaders());
            template.exchange("https://www.google.com", HttpMethod.POST, reqEntity, String.class);
        } catch (Exception ex) {

        }

        try {
            log.info("\n\n\n\nNow with explicit set of length:\n\n\n\n\n");
            HttpHeaders customHeadersWithLength = new HttpHeaders();
            customHeadersWithLength.setContentLength(0);

            HttpEntity<?> reqEntityWithLengthSet = new HttpEntity<>(customHeadersWithLength);
            template.exchange("https://www.google.com", HttpMethod.POST, reqEntityWithLengthSet, String.class);
        } catch (Exception ex) {

        }

        try {

            log.info("\n\n\n\nNow with Empty string as body:\n\n");

            HttpEntity<?> reqEntityWithEmpty = new HttpEntity<>("", new HttpHeaders());
            template.exchange("https://www.google.com", HttpMethod.POST, reqEntityWithEmpty, String.class);
        } catch (Exception ex) {

        }

    }

}
