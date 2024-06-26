package vl.example.accountsexaminer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import vl.example.accountsexaminer.external.ExternalRestServiceRestClientImpl;

@Configuration
public class ExaminerBeans {

    @Bean
    public ExternalRestServiceRestClientImpl externalClient(@Value("${services.external.url:}") String externalUrl) {

        return new ExternalRestServiceRestClientImpl(RestClient.builder()
                .baseUrl(externalUrl)
                .build());
    }
}
