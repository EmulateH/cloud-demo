package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.itcast.feign.pojo.User;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

/*    @Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.远程调用user服务,根据id查询订单所属的用户信息
/*        //String url = "http://localhost:8081/user/"+order.getUserId();
        String url = "http://userservice/user/"+order.getUserId();
        //String userJson = restTemplate.getForObject(url, String.class);
        User user = restTemplate.getForObject(url, User.class);*/
        //2.利用Feign发起http请求,查询用户
        User user = userClient.findById(order.getUserId());
        // 3.将远程调用的结果封装到Order对象中
        order.setUser(user);
        // 4.返回
        return order;
    }
}
