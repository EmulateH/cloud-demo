package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    int port;

    @Autowired
    private PatternProperties patternProperties;

/*    @Value("${pattern.dateformat}")
    private String dateformat;*/

    /**
     * 路径： /user/110
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        User user =userService.queryById(id);
        user.setAddress(""+port);
        return user;
    }

    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    @GetMapping("prop")
    public PatternProperties prop(){
        return patternProperties;
    }

    @PostMapping("save")
    public String save(@RequestBody User user){
        System.out.println(user);
        return "Success";
    }
}
