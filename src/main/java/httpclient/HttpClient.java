package httpclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

public class HttpClient {

    private static final String BASE_URL = "https://reqres.in/api/";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    public <T> ResponseBody<T> get(String urlPart, Class<T> clazz) {
        String url = format("%s%s", BASE_URL, urlPart);
        Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return getResponseAsObject(response, clazz);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Exception during sending get request");
        }
    }

    public <T> ResponseBody<T> post(String urlPart, String jsonBody, Class<T> clazz) {
        String url = format("%s%s", BASE_URL, urlPart);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return getResponseAsObject(response, clazz);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Exception during sending post request");
        }
    }

    public <T> ResponseBody<T> put(String urlPart, String jsonBody, Class<T> clazz) {
        String url = format("%s%s", BASE_URL, urlPart);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder().url(url).put(requestBody).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return getResponseAsObject(response, clazz);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Exception during sending put request");
        }
    }

    public <T> ResponseBody<T> patch(String urlPart, String jsonBody, Class<T> clazz) {
        String url = format("%s%s", BASE_URL, urlPart);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder().url(url).patch(requestBody).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return getResponseAsObject(response, clazz);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Exception during sending patch request");
        }
    }

    public Response delete(String urlPart) {
        String url = format("%s%s", BASE_URL, urlPart);
        Request request = new Request.Builder().url(url).delete().build();
        Call call = CLIENT.newCall(request);
        try {
            return call.execute();
        } catch (IOException e) {
            throw new UnsupportedOperationException("Exception during sending delete request");
        }
    }

    private <T> ResponseBody<T> getResponseAsObject(Response response, Class<T> clazz) throws IOException {
        String responseBody = Objects.requireNonNull(response.body()).string();
        T body = GSON.fromJson(responseBody, clazz);
        return new ResponseBody<>(response.code(), body);
    }
}
