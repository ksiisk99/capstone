package assignment.capstone.tistory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientInfo {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
}