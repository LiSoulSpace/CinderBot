package xyz.soulspace.cinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BlogSummary {
    @Schema(title = "id", required = true)
    int id;
    @Schema(title = "title", required = true)
    String title;
    @Schema(title = "updateTime", required = true)
    String updateTime;
    @Schema(title = "summary", required = true)
    String summary;
}
