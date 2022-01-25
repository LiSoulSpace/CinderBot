package xyz.soulspace.cinder.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReString {
    //指示是否成功
    private boolean success;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    public ReString() {
    }

    public ReString(boolean success) {
        this.success = success;
    }

    public ReString(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ReString(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
