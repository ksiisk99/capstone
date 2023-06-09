package assignment.capstone.tistory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogInfo {
    private String accessToken;
    private Integer postId;
    private String blogName;
}
