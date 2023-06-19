package assignment.capstone.tistory;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatGptHandlerTest {

    @Test
    void createGptContent() {
        ChatGptHandler handler = new ChatGptHandler();

        List<String> result = handler.generateText("안녕하세요 반가워요");

        assertTrue(result.size() > 0);
    }
}