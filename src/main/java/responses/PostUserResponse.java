package responses;

import lombok.Data;

@Data
public class PostUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
