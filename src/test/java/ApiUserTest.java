import httpclient.ResponseBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import responses.GetSingleUserResponse;
import responses.GetUsersListResponse;
import responses.PostUserResponse;
import responses.PutPatchUserResponse;

import static java.lang.String.format;

public class ApiUserTest extends AbstractApiTest {

    @Test
    public void checkGetListUsersResponse() {
        ResponseBody<GetUsersListResponse> response = httpClient.get("users?page=2", GetUsersListResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getPage(), 2,
            format("Expected page number [2], but actual [%d]", response.getBody().getPage()));
        Assert.assertEquals(response.getBody().getData().size(), 6,
            format("Expected users count [6], but actual [%d]", response.getBody().getData().size()));
    }

    @Test
    public void checkGetSingleUserResponse() {
        ResponseBody<GetSingleUserResponse> response = httpClient.get("users/2", GetSingleUserResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getData().getId(), 2,
            format("Expected users id [2], but actual [%d]", response.getBody().getData().getId()));
    }

    @Test
    public void checkGetClientNotFoundResponse() {
        ResponseBody<GetSingleUserResponse> response = httpClient.get("users/23", GetSingleUserResponse.class);
        Assert.assertEquals(response.getStatusCode(), 404,
            codeMessage(404, response.getStatusCode()));
    }

    @Test
    public void checkPostUserResponse() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        ResponseBody<PostUserResponse> response = httpClient.post("unknown/23", requestBody,
            PostUserResponse.class);
        Assert.assertEquals(response.getStatusCode(), 201,
            codeMessage(201, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getName(), "morpheus",
            format("Expected name [morpheus], but actual [%s]", response.getBody().getName()));
        Assert.assertEquals(response.getBody().getJob(), "leader",
            format("Expected job [leader], but actual [%s]", response.getBody().getJob()));
    }

    @Test
    public void checkPutUserResponse() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        ResponseBody<PutPatchUserResponse> response = httpClient.put("users/2", requestBody,
            PutPatchUserResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getName(), "morpheus",
            format("Expected name [morpheus], but actual [%s]", response.getBody().getName()));
        Assert.assertEquals(response.getBody().getJob(), "zion resident",
            format("Expected job [zion resident], but actual [%s]", response.getBody().getJob()));
    }

    @Test
    public void checkPatchUserResponse() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        ResponseBody<PutPatchUserResponse> response = httpClient.patch("users/2", requestBody,
            PutPatchUserResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getName(), "morpheus",
            format("Expected name [morpheus], but actual [%s]", response.getBody().getName()));
        Assert.assertEquals(response.getBody().getJob(), "zion resident",
            format("Expected job [zion resident], but actual [%s]", response.getBody().getJob()));
    }

    @Test
    public void checkDeleteUserResponse() {
        Response response = httpClient.delete("users/2");
        Assert.assertEquals(response.code(), 204,
            codeMessage(204, response.code()));
    }
}
