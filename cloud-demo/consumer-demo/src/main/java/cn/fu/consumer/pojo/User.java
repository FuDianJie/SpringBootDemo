package cn.fu.consumer.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private Integer id;
    private String name;

    //备注
    private String remark;
}
