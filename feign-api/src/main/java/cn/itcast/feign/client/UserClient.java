package cn.itcast.feign.client;

import cn.itcast.feign.config.DefaultFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cn.itcast.feign.pojo.User;

// 注解代表 当前接口时feign客户端
@FeignClient(value = "userservice",configuration = DefaultFeignConfiguration.class) //要调用哪个微服务
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);

    @PostMapping("/user/save")
    public String save(@RequestBody User user);
}
