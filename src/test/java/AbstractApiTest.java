import httpclient.HttpClient;

import static java.lang.String.format;

public class AbstractApiTest {
    protected final HttpClient httpClient = new HttpClient();

    protected String codeMessage(int expectedCode, int actualCode) {
        return format("Expected status code [%d], but actual [%d]", expectedCode, actualCode);
    }
}
