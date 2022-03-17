package xyz.soulspace.cinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class BlogUpdateParam {
    @NotEmpty
    @Schema(title = "id",required = true)
    int id;
    String markdownEntity;
    String htmlEntity;
    String title;
}
