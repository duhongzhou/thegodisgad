package top.thegodisgad.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.commodity.entity.Commodity;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_commodity(商品)】的数据库操作Mapper
 * @createDate 2022-05-19 16:14:52
 * @Entity top.thegodisgad.community.entity.Commodity
 */
@Repository
@Mapper
public interface CommodityMapper {
    int insert(Commodity commodity);

    int updateCommodityStatusByCommodityId(@Param(value = "commodityStatus") Integer commodityStatus, @Param(value = "commodityId") Long commodityId);

    int update(Commodity commodity);

    List<Commodity> select(@Param(value = "start") Integer start,@Param(value = "length")Integer length);

    List<Commodity> selectByUserId(@Param(value = "userId") Long userId);


}




