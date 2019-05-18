package cn.fu.consumer.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "fu")
@Component
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;

    //备注
    @Transient  //瞬时的  表示不会进入通用mapper条件中
    private String remark;
}
