package assignment.capstone.tistory;

import assignment.capstone.dto.BlogInfo;
import assignment.capstone.dto.Comment;
import assignment.capstone.entity.Blog;
import assignment.capstone.service.tistory.CommentReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentReaderTest {

    @Test
    void read() {
        CommentReader reader = new CommentReader();
        Blog blog = new Blog(1L, "accesssToken", 4, "blogName");


        List<Comment> comments = reader.read(blog);

        assertEquals(1, comments.size());
    }
}