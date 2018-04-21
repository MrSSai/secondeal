package com.secondeal.controller.org;

import com.secondeal.controller.BaseController;
import com.secondeal.model.org.Order;
import com.secondeal.service.account.AccountI;
import com.secondeal.service.org.OrderI;
import com.secondeal.util.CodeMsg;
import com.secondeal.util.JavaWebToken;
import com.secondeal.util.Result;
import com.secondeal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderI orderI;


    @PostMapping("/add")
    public Object addOrder(){
        Map<String, Object> pd = this.getParam();
        String good_title = StringUtil.parseString(pd.get("good_title"));
        String good_uuid = StringUtil.parseString(pd.get("good_uuid"));
        Double good_discounted = StringUtil.parseDouble(pd.get("good_discounted"));
        String good_intro = StringUtil.parseString(pd.get("good_intro"));
        String token = StringUtil.parseString(pd.get("token"));
//        String token = this.getHeadersInfo();
        String uuid = StringUtil.getUUID();
        Map<String, Object> m = JavaWebToken.parserJavaWebToken(token);

        String buy_uuid = StringUtil.parseString(m.get("uuid"));

        Order order = new Order();
        order.setBuyUuid(buy_uuid);
        order.setGoodDiscounted(good_discounted);
        order.setCreateTime(StringUtil.getDate());
        order.setGoodIntro(good_intro);
        order.setGoodTitle(good_title);
        order.setGoodUuid(good_uuid);
        order.setUuid(uuid);
        order.setState(0);
        int res = orderI.insert(order);
        if (res == 1) {
            return Result.success("添加成功");
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }

    @GetMapping("/getMyCart")
    public Object getMyCart(){
        String token = this.getHeadersInfo();
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiMjUwNWNjNWZmYmJjNDUwOTg2YTcwMmZhYmU3M2YyOWEiLCJ1c2VybmFtZSI6IjE3NTA1NjczNTIwIn0.mRbrCVEeU5oadHNOAwx4o0bj84lrCIPujtjv29uQmf4";
        Map<String, Object> m = JavaWebToken.parserJavaWebToken(token);
        String uuid = StringUtil.parseString(m.get("uuid"));
        List<Order> list = orderI.getByBuyUuid(uuid);
        if (list.size() != 0) {
            return Result.success(list);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }
}
