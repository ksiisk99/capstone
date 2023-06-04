package assignment.capstone.tistory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Comment {
    private String id;
    private String date;
    private String name;
    private String parentId;
    private String homepage;
    private String content;
    private boolean isOpen;
}
