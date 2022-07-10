package responses;

import bo.Support;
import bo.User;
import lombok.Data;

@Data
public class GetSingleUserResponse {
    private User data;
    private Support support;
}
