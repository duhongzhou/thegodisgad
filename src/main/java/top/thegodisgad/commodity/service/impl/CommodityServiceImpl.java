package top.thegodisgad.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.blog.service.BlogPostService;
import top.thegodisgad.commodity.entity.Commodity;
import top.thegodisgad.commodity.mapper.CommodityMapper;
import top.thegodisgad.commodity.service.CommodityService;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_commodity(商品)】的数据库操作Service实现
 * @createDate 2022-05-19 16:14:52
 */
@Service
public class CommodityServiceImpl
        implements CommodityService {
    BlogPostService blogPostService;
    CommodityMapper commodityMapper;

    @Autowired
    public CommodityServiceImpl(BlogPostService blogPostService, CommodityMapper commodityMapper) {
        this.blogPostService = blogPostService;
        this.commodityMapper = commodityMapper;
    }

    /**
     */
    @Override
    public Long add(Commodity commodity) {

        return null;
    }

    /**
     */
    @Override
    public int updateStatus(Commodity commodity) {
        return 0;
    }

    /**
     */
    @Override
    public int updateStatus(Integer commodityStatus, Long commodityId) {
        return 0;
    }

    /**
     */
    @Override
    public int update(Commodity commodity) {
        return 0;
    }

    /**
     */
    @Override
    public List<Commodity> select(Integer start, Integer length) {
        return null;
    }

    /**
     */
    @Override
    public List<Commodity> selectByUserId(Long userId) {
        return null;
    }

    /**
     */
    @Override
    public List<Commodity> selectByCommodityId(List<Long> conmodityIds) {
        return null;
    }
}




