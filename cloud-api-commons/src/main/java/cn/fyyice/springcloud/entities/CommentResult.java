package cn.fyyice.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/20 18:42
 * @description 全局返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code,String message){
        this(code,message,null);
    }

}
