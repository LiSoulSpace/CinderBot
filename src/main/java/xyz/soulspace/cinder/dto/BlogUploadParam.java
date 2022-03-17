package xyz.soulspace.cinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class BlogUploadParam {
    @NotEmpty
    @Schema(title = "标题",required = true)
    private String title;
    @NotEmpty
    @Schema(title = "markdown体",required = true)
    private String markdownEntity;
    @NotEmpty
    @Schema(title = "html体",required = true)
    private String htmlEntity;
    @NotEmpty
    @Schema(title = "用户名", required = true)
    private String username;
    @Schema(title = "总结",required = true)
    private String summary;
}
