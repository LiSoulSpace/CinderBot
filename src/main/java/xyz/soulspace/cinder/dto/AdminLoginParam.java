package xyz.soulspace.cinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminLoginParam {
    @NotEmpty
    @Schema(title = "用户名",required = true)
    private String username;
    @NotEmpty
    @Schema(title = "密码",required = true)
    private String password;
}
