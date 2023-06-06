package assignment.capstone.tistory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentWriterTest {
    @Test
    void writeComment() {
        CommentWriter commentWriter = new CommentWriter();
        String accessToken = "9c7723bb3ec4d8fed5ec807ac679a3ba_e4497d447feb9188909d65c28bfccaa9";
        Integer postId = 4;
        String blogName = "blog";
        String parentId = "15766892";
        String content = "world";
        boolean open = true;

        assertEquals(true, commentWriter.write(accessToken, blogName, postId, parentId, content, open));
    }
}