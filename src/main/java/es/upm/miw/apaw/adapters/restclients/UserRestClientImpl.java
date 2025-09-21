package es.upm.miw.apaw.adapters.restclients;

import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service("userRestClient")
public class UserRestClientImpl implements UserRestClient {
    public static final String ID = "/{id}";
    private final String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public UserRestClientImpl(RestTemplate restTemplate, @Value("${miw.apaw.user}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public UserDto readById(UUID id) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(baseUrl + ID, UserDto.class, id);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new BadGatewayException("User not found with ID: " + id);
        } else {
            throw new BadGatewayException("Error retrieving user. Status: " + response.getStatusCode());
        }
    }

    @Override
    public UserDto readByMobile(String mobile) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(baseUrl + ID, UserDto.class, mobile);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new BadGatewayException("User not found with MOBILE: " + mobile);
        } else {
            throw new BadGatewayException("Error retrieving user. Status: " + response.getStatusCode());
        }
    }
}
