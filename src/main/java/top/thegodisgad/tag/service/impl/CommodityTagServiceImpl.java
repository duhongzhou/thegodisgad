package top.thegodisgad.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.commodity.entity.Commodity;
import top.thegodisgad.commodity.service.CommodityService;
import top.thegodisgad.tag.entity.CommodityTag;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.mapper.CommodityTagMapper;
import top.thegodisgad.tag.service.CommodityTagService;
import top.thegodisgad.tag.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yhdhz
 * @description 针对表【tb_commodity_tag(拥有的标签(所有标签))】的数据库操作Service实现
 * @createDate 2022-05-19 16:16:13
 */
@Service
public class CommodityTagServiceImpl
        implements CommodityTagService {
    TagService tagService;
    CommodityTagMapper commodityTagMapper;
    CommodityService commodityService;

    @Autowired
    public CommodityTagServiceImpl(TagService tagService, CommodityTagMapper commodityTagMapper, CommodityService commodityService) {
        this.tagService = tagService;
        this.commodityTagMapper = commodityTagMapper;
        this.commodityService = commodityService;
    }


    @Override
    public int add(CommodityTag commodityTag, Tag tag) {
        Long tagIdAndNullCreate = tagService.findTagIdAndNullCreate(tag);
        commodityTag.setTagId(tagIdAndNullCreate);
        int insert = commodityTagMapper.insert(commodityTag);
        return insert;
    }

    @Override
    public int delete(CommodityTag commodityTag) {
        int delete = commodityTagMapper.delete(commodityTag);
        return delete;
    }

    @Override
    public List<Tag> findByCommodityId(Long commodityId) {
        List<CommodityTag> blogTags = commodityTagMapper.selectByCommodityId(commodityId);
        List<Long> tagIds = blogTags.stream().map(CommodityTag::getTagId).collect(Collectors.toList());
        List<Tag> tags = tagService.findTagByTagIds(tagIds);
        return tags;
    }

    @Override
    public List<Commodity> findByTagId(Long tagId) {
        //博客通过商品id获取商品
        List<CommodityTag> commodityTags = commodityTagMapper.selectByCommodityId(tagId);
        List<Long> collect = commodityTags.stream().map(CommodityTag::getTagId).collect(Collectors.toList());
        List<Commodity> commodities = commodityService.selectByCommodityId(collect);
        return commodities;
    }
}




