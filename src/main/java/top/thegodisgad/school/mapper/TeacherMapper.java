package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.Teacher;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_teacher(就读于)】的数据库操作Mapper
 * @createDate 2022-05-19 13:37:31
 * @Entity top.thegodisgad.school.entity.Teacher
 */
@Repository
@Mapper
public interface TeacherMapper {
    int insert(Teacher teacher);

    int delete(Teacher teacher);

    Teacher findAllByUserId(@Param(value = "userId") Long userId);

    List<Teacher> findAllBy(Teacher teacher);
}




