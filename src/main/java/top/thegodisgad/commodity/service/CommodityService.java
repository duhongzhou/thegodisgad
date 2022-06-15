package top.thegodisgad.commodity.service;

import top.thegodisgad.commodity.entity.Commodity;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_commodity(商品)】的数据库操作Service
* @createDate 2022-05-19 16:14:52
*/
public interface CommodityService  {
    
    public Long add (Commodity commodity);

    public int  updateStatus(Commodity commodity);
    public int  updateStatus( Integer commodityStatus,Long commodityId);
    int update(Commodity commodity);
    List<Commodity> select(Integer start,Integer length);

    List<Commodity> selectByUserId( Long userId);

    List<Commodity> selectByCommodityId(List<Long>  conmodityIds);
}
