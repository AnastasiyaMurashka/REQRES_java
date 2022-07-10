package responses;

import bo.User;
import lombok.Data;

import java.util.List;

@Data
public class GetUsersListResponse extends AbstractListResponse {
    private List<User> data;
}
