package assignment.capstone.tistory;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatGptHandlerTest {

    @Test
    void createGptContent() {
        ChatGptHandler handler = new ChatGptHandler();
        List<Comment> comments = List.of(new Comment("1", "date", "name", "parentId", "homepage", "content", true));

        List<String> result = handler.generateText(comments);

        assertTrue(result.size() > 0);
    }
}