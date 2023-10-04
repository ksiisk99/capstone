package assignment.capstone.service.tistory;

import assignment.capstone.dto.ClientInfo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AccessTokenParser {

    public String getAccessToken(ClientInfo clientInfo) {
        try {
            String url = "https://www.tistory.com/oauth/access_token?"
                    + "client_id=" + clientInfo.getClientId() + "&"
                    + "client_secret=" + clientInfo.getClientSecret() + "&"
                    + "redirect_uri=" + clientInfo.getRedirectUri() + "&"
                    + "code=" + clientInfo.getCode() + "&"
                    + "grant_type=authorization_code";

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String accessToken = br.readLine().split("=")[1];

            System.out.println("ACCESS TOKEN: " + accessToken);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();

            return "Error";
        }
    }
}
