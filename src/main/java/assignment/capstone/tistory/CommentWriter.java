package assignment.capstone.tistory;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CommentWriter {

    public boolean write(String accessToken, String blogName, Integer postId, String parentId, String content, boolean open) {
        try {
            String url = "https://www.tistory.com/apis/comment/write?" +
                    "access_token=" + accessToken +
                    "&output=json" +
                    "&blogName=" + blogName +
                    "&postId=" + postId +
                    "&parentId=" + parentId +
                    "&content=" + content +
                    "&secret=" + (open ? 0 : 1);

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
