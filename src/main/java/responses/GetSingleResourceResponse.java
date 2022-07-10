package responses;

import bo.Resource;
import bo.Support;
import lombok.Data;

@Data
public class GetSingleResourceResponse {
    private Resource data;
    private Support support;
}
