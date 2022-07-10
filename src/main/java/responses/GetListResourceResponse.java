package responses;

import bo.Resource;

import java.util.List;

public class GetListResourceResponse extends AbstractListResponse {
    private List<Resource> data;

    public List<Resource> getData() {
        return data;
    }
}
