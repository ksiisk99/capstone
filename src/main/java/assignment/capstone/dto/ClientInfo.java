package assignment.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientInfo {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private Integer postId;
}