package de.tum.ar.researchplatform.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.ar.researchplatform.exception.CustomLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static de.tum.ar.researchplatform.util.Constants.*;
import static org.springframework.http.MediaType.TEXT_HTML;

/**
 * Created by karthik on 5/16/2020
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean attemptTempLogin(String userId, String password) throws CustomLoginException {
        if(userId.equals("user") && password.equals("password"))
            return false;
        else if(userId.equals("admin") && password.equals("password"))
            return true;
        throw new CustomLoginException(LOGIN_FAILED_MSG);
    }

    @Override
    public MultiValueMap<String, String> attemptLogin(String userId, String password) {
        //TODO: Implement API call here
        MultiValueMap<String, String> cookies = new LinkedMultiValueMap<String, String>();
        WebClient webClient = WebClient.builder()
                .baseUrl(TUM_ONLINE_BASE_URL)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        ClientResponse clientResponse = webClient
                .post()
                .uri(TUM_ONLINE_LOGIN_URL)
                .body(BodyInserters.fromFormData(TUM_ONLINE_LOGIN_USERID_PARAM, userId)
                        .with(TUM_ONLINE_LOGIN_PASSWORD_PARAM, password))
                .exchange()
                .map(response -> response)
                .block();
        Mono<String> body = clientResponse.bodyToMono(String.class);
        for(String key: clientResponse.cookies().keySet()) {
            cookies.put(key, Arrays.asList(clientResponse.cookies().get(key).get(0).getValue()));
        }
        return cookies;
    }

    @Override
    public MultiValueMap<String, String> attemptLogout(String userId, String sessionId) {
        //TODO: Implement API call here
        MultiValueMap<String, String> cookies = new LinkedMultiValueMap<String, String>();
        WebClient webClient = WebClient.builder()
                .baseUrl(TUM_ONLINE_BASE_URL)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .build();
        ClientResponse clientResponse = webClient
                .get()
                .uri(TUM_ONLINE_LOGOUT_URL)
                .exchange()
                .map(response -> response)
                .block();
        Mono<String> body = clientResponse.bodyToMono(String.class);
        for(String key: clientResponse.cookies().keySet()) {
            cookies.put(key, Arrays.asList(clientResponse.cookies().get(key).get(0).getValue()));
        }
        return cookies;
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().encoder(new Jackson2JsonEncoder(new ObjectMapper(), TEXT_HTML));
        clientCodecConfigurer.customCodecs().decoder(new Jackson2JsonDecoder(new ObjectMapper(), TEXT_HTML));
    }
}
