package assignment.capstone.service.gpt;

import assignment.capstone.dto.Comment;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ChatGptHandler {
    @Value("${CHAT_GPT_KEY}")
    private String CHAT_GPT_KEY;
    private static final String GPT_URL = "https://api.openai.com/v1/completions";

    public List<String> createCommentResponses(List<Comment> comments) {
        List<String> result = new ArrayList<>();
        for (Comment comment : comments) {
            result.add(createGptResponse(comment));

            try {
                Thread.sleep(30000L); //chat gpt 분당 요청 제한 조절
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private String createGptResponse(Comment comment) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + CHAT_GPT_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", comment.getContent()); //일단 임시로 1개만 해놓자. 나중에 여러개 처리
        requestBody.put("max_tokens", 100);
        requestBody.put("temperature", 0.7F);
        requestBody.put("role", "user");
        requestBody.put("presence_penalty", 0);
        requestBody.put("n", 1);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GPT_URL, requestEntity, String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
            JsonNode choicesNode = rootNode.get("choices");

            Iterator<JsonNode> iterator = choicesNode.elements();
            while (iterator.hasNext()) {
                JsonNode choiceNode = iterator.next();
                String text = choiceNode.get("text").asText();
                int startIndex = text.indexOf("\n\n");
                if (startIndex != -1) {
                    return text.substring(startIndex + 2);
                } else {
                    return text;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; //throw를 던지자.
    }
}
