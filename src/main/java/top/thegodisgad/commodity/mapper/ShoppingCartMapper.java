package top.thegodisgad.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.commodity.entity.ShoppingCart;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_shopping_cart(购物车)】的数据库操作Mapper
 * @createDate 2022-05-19 16:23:21
 * @Entity top.thegodisgad.commodity.entity.ShoppingCart
 */
@Repository
@Mapper
public interface ShoppingCartMapper {
    int insert(ShoppingCart shoppingCart);

    int updateIsValidByCommodityIdAndUserId(@Param(value = "userId") Long userId,
                                            @Param(value = "commodityId") Long commodityId,
                                            @Param(value = "isValid") Integer isValid);

    List<ShoppingCart> selectByUserId(@Param(value = "userId") Long userId);

    ShoppingCart selectByCommodityIdAndUserId(@Param(value = "userId") Long userId,
                                              @Param(value = "commodityId") Long commodityId);
}




