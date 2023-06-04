package assignment.capstone.tistory;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentReaderTest {

    @Test
    void read() {
        CommentReader reader = new CommentReader();
        String accessToken = "9c7723bb3ec4d8fed5ecc679a3ba_e4497d447feb9188909d65c28bfccaa9";
        Integer postId = 4;
        String blogName = "blog";

        List<Comment> comments = reader.read(accessToken, postId, blogName);

        assertEquals(1, comments.size());
    }
}