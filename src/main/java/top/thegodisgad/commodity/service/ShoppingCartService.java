package top.thegodisgad.commodity.service;

import top.thegodisgad.commodity.entity.ShoppingCart;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_shopping_cart(购物车)】的数据库操作Service
* @createDate 2022-05-19 16:23:21
*/
public interface ShoppingCartService  {
    boolean add(ShoppingCart shoppingCart);
    boolean updateIsValidByCommodityIdAndUserId(Long userId,Long commodityId,Integer isValid);
    boolean delete(ShoppingCart shoppingCart);

    List<ShoppingCart> selectByUserId(Long userId);
}
