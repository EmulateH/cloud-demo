package cn.itcast.order.web;

import cn.itcast.feign.client.UserClient;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.itcast.feign.pojo.User;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId,
                                    @RequestHeader(value = "Truth",required = false) String name) {
        System.err.println("请求头中携带的name = " + name);
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    @Autowired
    private UserClient userClient;

    @GetMapping("save")
    public String saveUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("测试 远程保存");
        user.setAddress("");
        String save = userClient.save(user);
        // 根据id查询订单并返回
        return save;
    }
}
