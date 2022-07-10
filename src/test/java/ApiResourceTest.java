import httpclient.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import responses.GetListResourceResponse;
import responses.GetSingleResourceResponse;

import static java.lang.String.format;

public class ApiResourceTest extends AbstractApiTest {

    @Test
    public void checkGetListResourceResponse() {
        ResponseBody<GetListResourceResponse> response = httpClient.get("unknown",
            GetListResourceResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getData().size(), 6,
            format("Expected resource count [6], but actual [%d]", response.getBody().getData().size()));
    }

    @Test
    public void checkGetSingleResourceResponse() {
        ResponseBody<GetSingleResourceResponse> response = httpClient.get("unknown/2",
            GetSingleResourceResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200,
            codeMessage(200, response.getStatusCode()));
        Assert.assertEquals(response.getBody().getData().getId(), 2,
            format("Expected resource id [2], but actual [%d]", response.getBody().getData().getId()));
    }

    @Test
    public void checkGetResourceNotFoundResponse() {
        ResponseBody<GetSingleResourceResponse> response = httpClient.get("unknown/23",
            GetSingleResourceResponse.class);
        Assert.assertEquals(response.getStatusCode(), 404,
            codeMessage(404, response.getStatusCode()));
    }
}
