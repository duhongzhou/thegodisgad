package top.thegodisgad.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.blog.entity.BlogOperation;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_operation(博客文章操作)】的数据库操作Mapper
 * @createDate 2022-05-19 16:17:38
 * @Entity top.thegodisgad.blog.entity.BlogOperation
 */
@Repository
@Mapper
public interface BlogOperationMapper {
    int insertAll(BlogOperation blogOperation);

    int updateOperationTypeByUserIdAndBlogIdInt(BlogOperation blogOperation);


    BlogOperation selectByUserIdAndBlogIdBlogOperation(@Param(value = "blogId") Long blogId, @Param(value = "userId") Long userId);

    List<Long> selectByUserIdBlogOperations(@Param(value = "userId") Long userId);

    int selectByBlogIdBlogOperations(@Param(value = "blogId") Long blogId, @Param(value = "operationType") Integer operationType);

}




