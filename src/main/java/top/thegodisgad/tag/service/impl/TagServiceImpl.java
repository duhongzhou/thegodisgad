package top.thegodisgad.tag.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.exception.TagException;
import top.thegodisgad.tag.exception.TagExceptionEnum;
import top.thegodisgad.tag.mapper.TagMapper;
import top.thegodisgad.tag.service.TagService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.IDUtils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_tag(标签)】的数据库操作Service实现
* @createDate 2022-05-19 16:16:20
*/
@Service
public class TagServiceImpl 
    implements TagService{
    TagMapper tagMapper;
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(Tag tag) {
        Long id;
        while(true) {
            try {
                id = IDUtils.createId();
                tag.setTagId(id);
                int insert = tagMapper.insert(tag);
                break;
            } catch (SQLIntegrityConstraintViolationException e) {
                logger.info("添加标签:",e);
            }
        }
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Tag tag) {
        if(tag==null)
        {
            throw new TagException(TagExceptionEnum.UNKNOWN);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long tagId) {
        int delete = tagMapper.delete(tagId);
        return delete==1?true:false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> findUserCreateTag(User user) {
        return findUserCreateTag(user.getUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> findUserCreateTag(Long userId) {
        List<Tag> tags = tagMapper.selectByCreatorId(userId);
        return tags;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> findTagByTagIds(List<Long> tageId) {
        List<Tag> tags = tagMapper.selectByTagId((ArrayList<Long>) tageId);
        return tags;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> findTagByTagId(Long tageId) {
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(tageId);
        return findTagByTagIds(longs);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> findTagByTagName(String tageName) {
        List<Tag> tags = tagMapper.selectByTagName(tageName);
        return tags;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long findTagIdByTagNameAndTagProperty(Tag tag) {
        Long aLong = tagMapper.selectTagByTagNameAndTagProperty(tag);
        return aLong;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long findTagIdAndNullCreate(Tag tag){

        Long tagIdByTagNameAndTagProperty = findTagIdByTagNameAndTagProperty(tag);
        if(tagIdByTagNameAndTagProperty ==null)
        {
            tagIdByTagNameAndTagProperty= add(tag);
        }
        return tagIdByTagNameAndTagProperty;
    }
}




