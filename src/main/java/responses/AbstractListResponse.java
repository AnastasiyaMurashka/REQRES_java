package responses;

import bo.Support;
import lombok.Data;

@Data
public class AbstractListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private Support support;
}
