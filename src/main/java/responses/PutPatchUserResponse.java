package responses;

import lombok.Data;

@Data
public class PutPatchUserResponse {
    private String name;
    private String job;
    private String updatedAt;
}
