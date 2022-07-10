import bo.User;
import httpclient.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import responses.GetUsersListResponse;
import responses.PostErrorResponse;
import responses.PostRegisterResponse;

import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static utils.RandomStringGenerator.getRandCharSequence;

public class ApiRegisterTest extends AbstractApiTest {

    private User user;
    private String password;
    private static final String ERROR_MESSAGE = "Missing password";

    @Test
    public void checkPostSuccessfulRegisterResponse() {
        Random rand = new Random();
        ResponseBody<GetUsersListResponse> responseUsers = httpClient.get("users?page=2", GetUsersListResponse.class);
        List<User> users = responseUsers.getBody().getData();
        user = users.get(rand.nextInt(users.size()));
        password = getRandCharSequence(7);
        String requestBody = "{\"email\": \"" + user.getEmail() + "\", \"password\": \"" + password + "\"}";
        ResponseBody<PostRegisterResponse> response = httpClient.post("register", requestBody,
            PostRegisterResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertFalse(response.getBody().getToken().isEmpty(), "Token is empty");
    }

    @Test
    public void checkPostUnsuccessfulRegisterResponse() {
        String requestBody = "{\"email\": \"sydney@fife\"}";
        ResponseBody<PostErrorResponse> response = httpClient.post("register", requestBody,
            PostErrorResponse.class);
        Assert.assertEquals(response.getStatusCode(), 400,
            codeMessage(400, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getError(), ERROR_MESSAGE,
            format("Expected error message [%s], but actual [%s]", ERROR_MESSAGE, response.getBody().getError()));
    }

    @Test(dependsOnMethods = "checkPostSuccessfulRegisterResponse")
    public void checkPostSuccessfulLoginResponse() {
        String requestBody = "{\"email\": \"" + user.getEmail() + "\", \"password\": \"" + password + "\"}";
        ResponseBody<PostRegisterResponse> response = httpClient.post("login", requestBody,
            PostRegisterResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertFalse(response.getBody().getToken().isEmpty(), "Token is empty");
    }

    @Test
    public void checkPostUnsuccessfulLoginResponse() {
        String requestBody = "{\"email\": \"sydney@fife\"}";
        ResponseBody<PostErrorResponse> response = httpClient.post("login", requestBody,
            PostErrorResponse.class);
        Assert.assertEquals(response.getStatusCode(), 400,
            codeMessage(400, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getError(), ERROR_MESSAGE,
            format("Expected error message [%s], but actual [%s]", ERROR_MESSAGE, response.getBody().getError()));
    }
}
