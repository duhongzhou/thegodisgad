package top.thegodisgad.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.commodity.entity.ShoppingCart;
import top.thegodisgad.commodity.exception.CommodityException;
import top.thegodisgad.commodity.exception.CommodityExceptionEnum;
import top.thegodisgad.commodity.mapper.ShoppingCartMapper;
import top.thegodisgad.commodity.service.ShoppingCartService;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-05-19 16:23:21
*/
@Service
public class ShoppingCartServiceImpl 
    implements ShoppingCartService{
    ShoppingCartMapper shoppingCartMapper;
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public boolean add(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCart1 = shoppingCartMapper.selectByCommodityIdAndUserId(shoppingCart.getUserId(), shoppingCart.getCommodityId());

        if (shoppingCart1 !=null){
            shoppingCartMapper.insert(shoppingCart);
        }else{
            if(shoppingCart1.getIsValid()==1){
                throw new CommodityException(CommodityExceptionEnum.DUPLICATE_ADDITION);
            }else{
                updateIsValidByCommodityIdAndUserId(shoppingCart1.getUserId(),shoppingCart1.getCommodityId(),1);
            }
        }
        return true;
    }

    @Override
    public boolean updateIsValidByCommodityIdAndUserId(Long userId, Long commodityId, Integer isValid) {
        int i = shoppingCartMapper.updateIsValidByCommodityIdAndUserId(userId, commodityId, isValid);
        return i==1;
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {

        return  updateIsValidByCommodityIdAndUserId(shoppingCart.getUserId(),shoppingCart.getCommodityId(),0);
    }

    @Override
    public List<ShoppingCart> selectByUserId(Long userId) {
        return shoppingCartMapper.selectByUserId(userId);
    }
}




