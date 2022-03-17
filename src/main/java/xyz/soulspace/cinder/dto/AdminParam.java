package xyz.soulspace.cinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户注册参数
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class AdminParam {
    @NotEmpty
    @Schema(title = "用户名", required = true)
    private String username;
    @NotEmpty
    @Schema(title = "密码", required = true)
    private String password;
    @Schema(title = "用户头像")
    private String icon;
    @Email
    @Schema(title = "邮箱")
    private String email;
    @Schema(title = "用户昵称")
    private String nickName;
    @Schema(title = "备注")
    private String note;
}
