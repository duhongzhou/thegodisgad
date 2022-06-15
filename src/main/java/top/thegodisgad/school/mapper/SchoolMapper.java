package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.School;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_school(学校)】的数据库操作Mapper
 * @createDate 2022-05-19 16:13:14
 * @Entity top.thegodisgad.school.entity.School
 */
@Repository
@Mapper
public interface SchoolMapper {
    int insert(School school);

    int delete(@Param(value = "schoolId") Long schoolId);

    int update(School school);

    School selectBySchoolId(@Param(value = "schoolId") Long schoolId);

    List<School> select(School school);

    List<School> selectAll();
}




