package httpclient;

import lombok.Data;
import lombok.NonNull;

@Data
public class ResponseBody<T> {
    @NonNull
    private int statusCode;
    @NonNull
    private T body;
}
