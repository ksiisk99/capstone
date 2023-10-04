package assignment.capstone.service.tistory;

import assignment.capstone.entity.Blog;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class CommentWriter {

    public boolean write(Blog blog, String parentId, String content, boolean open) {
        try {
            String url = "https://www.tistory.com/apis/comment/write?" +
                    "access_token=" + URLEncoder.encode(blog.getAccessToken(), "UTF-8") +
                    "&output=json" +
                    "&blogName=" + URLEncoder.encode(blog.getBlogName(), "UTF-8") +
                    "&postId=" + URLEncoder.encode(String.valueOf(blog.getPostId()), "UTF-8") +
                    "&parentId=" + URLEncoder.encode(parentId, "UTF-8") +
                    "&content=" + URLEncoder.encode(content, "UTF-8") +
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
