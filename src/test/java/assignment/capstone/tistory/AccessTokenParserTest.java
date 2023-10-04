package assignment.capstone.tistory;

import assignment.capstone.dto.ClientInfo;
import assignment.capstone.service.tistory.AccessTokenParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenParserTest {

    @Test
    void getAccessToken() {
        AccessTokenParser parser = new AccessTokenParser();

        ClientInfo clientInfo = new ClientInfo("clientId",
                "clientSecret",
                "redirectUri",
                "code",
                4);

        assertDoesNotThrow(() -> {
            parser.getAccessToken(clientInfo);
        });
    }
}