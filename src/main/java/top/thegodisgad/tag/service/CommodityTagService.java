package top.thegodisgad.tag.service;

import top.thegodisgad.commodity.entity.Commodity;
import top.thegodisgad.tag.entity.CommodityTag;
import top.thegodisgad.tag.entity.Tag;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_commodity_tag(拥有的标签(所有标签))】的数据库操作Service
 * @createDate 2022-05-19 16:16:13
 */
public interface CommodityTagService {
    /**
     * add
     *
     * @return int
     * @author 杜洪洲
     * @description: add
     * @date 2022/5/27 14:52
     */
    public int add(CommodityTag commodityTag,Tag tag);

    /**
     * delete
     *
     * @return int
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:52
     */
    public int delete(CommodityTag commodityTag);

    /**
     * findByUserId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findByUserId
     * @date 2022/5/27 14:55
     */
    public List<Tag> findByCommodityId(Long commodityId);

    /**
     * findByTagId
     *
     * @return java.util.List<top.thegodisgad.commodity.entity.Commodity>
     * @author 杜洪洲
     * @description: findByTagId
     * @date 2022/5/27 14:55
     */
    public List<Commodity> findByTagId(Long tagId);
}
