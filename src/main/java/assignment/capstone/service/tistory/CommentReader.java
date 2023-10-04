package assignment.capstone.service.tistory;

import assignment.capstone.dto.Comment;
import assignment.capstone.entity.Blog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentReader {

    public List<Comment> read(Blog blog) {
        List<Comment> comments = new ArrayList<>();

        try {
            String url = "https://www.tistory.com/apis/comment/list?"
                    + "access_token=" + blog.getAccessToken() + "&"
                    + "output=json&"
                    + "blogName=" + blog.getBlogName() + "&"
                    + "postId=" + blog.getPostId();

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String response = br.readLine();
            System.out.println(response);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            JsonNode commentsNode = jsonNode
                    .path("tistory")
                    .path("item")
                    .path("comments");
                    //.path("comment");

            // JSON 배열로부터 comments 추출
            if (commentsNode.isArray()) {
                for (JsonNode commentNode : commentsNode) {
                    // 각 comment의 필요한 속성 추출
                    String id = commentNode.path("id").asText();
                    String date = commentNode.path("date").asText();
                    String name = commentNode.path("name").asText();
                    String parentId = commentNode.path("parentId").asText();
                    String homepage = commentNode.path("homepage").asText();
                    String comment = commentNode.path("comment").asText();
                    boolean open = Boolean.parseBoolean(commentNode.path("open").asText());

                    comments.add(new Comment(id, date, name, parentId, homepage, comment, open));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

}
