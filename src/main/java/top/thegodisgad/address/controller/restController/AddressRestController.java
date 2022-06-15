package top.thegodisgad.address.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.address.entity.Address;
import top.thegodisgad.address.service.AddressService;
import top.thegodisgad.aop.OnlyUser;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = {"/api/addresss"})
public class AddressRestController extends BaseRestController {
    AddressService addressService;
    RedisOperator redisOperator;

    @Autowired
    public AddressRestController(MessageUtils messageUtils, AddressService addressService, RedisOperator redisOperator) {
        super(messageUtils);
        this.addressService = addressService;
        this.redisOperator = redisOperator;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    @OnlyUser
    public Response add(@RequestBody Address address, HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        address.setUserId(user.getUserId());
        addressService.add(address);
        return Response.succeed(address, "添加地址成功");

    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @OnlyUser
    public Response delete(@Validated @RequestBody Address address, HttpServletRequest request) {

        User user = redisOperator.getUser(request);
        address.setUserId(user.getUserId());
        int delete = addressService.delete(address);
        Response response;
        if (delete == 1) {
            response = Response.succeed(address, "删除地址成功");
        } else {
            response = Response.failed(address, "删除地址失败");
        }
        return response;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    @OnlyUser
    public Response search(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        Long userId = user.getUserId();
        List<Address> search = addressService.search(userId);
        Response response;
        if (search.size() == 0) {
            response = Response.failed(null, "没有地址");
        } else {
            response = Response.succeed(search, "查询地址成功!");
        }
        return response;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    @OnlyUser
    public Response test(HttpServletRequest request) {

        return Response.succeed("test ok");
    }
}
