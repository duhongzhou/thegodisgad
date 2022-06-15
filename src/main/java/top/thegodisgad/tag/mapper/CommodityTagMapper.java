package top.thegodisgad.tag.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.tag.entity.CommodityTag;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_commodity_tag(拥有的标签(所有标签))】的数据库操作Mapper
 * @createDate 2022-05-19 16:16:13
 * @Entity top.thegodisgad.tag.entity.CommodityTag
 */
@Repository
@Mapper
public interface CommodityTagMapper {
    /**
     * insert
     *
     * @return int
     * @author 杜洪洲
     * @description: insert
     * @date 2022/5/27 14:37
     */
    public int insert(CommodityTag commodityTag);

    /**
     * selectByCommodityId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.CommodityTag>
     * @author 杜洪洲
     * @description: selectByCommodityId
     * @date 2022/5/27 14:37
     */
    public List<CommodityTag> selectByCommodityId(@Param(value = "commodityId") Long commodityId);

    /**
     * selectByTagId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.CommodityTag>
     * @author 杜洪洲
     * @description: selectByTagId
     * @date 2022/5/27 14:37
     */
    public List<CommodityTag> selectByTagId(@Param(value = "tagId") Long tagId);

    /**
     * delete
     *
     * @return int
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:37
     */
    public int delete(CommodityTag commodityTag);
}




