package assignment.capstone.tistory;

import assignment.capstone.dto.BlogInfo;
import assignment.capstone.dto.Comment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentReaderTest {

    @Test
    void read() {
        CommentReader reader = new CommentReader();
        BlogInfo blogInfo = new BlogInfo("accesssToken", 4, "blogName");


        List<Comment> comments = reader.read(blogInfo);

        assertEquals(1, comments.size());
    }
}