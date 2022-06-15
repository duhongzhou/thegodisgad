package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.Student;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_student(就读于)】的数据库操作Mapper
 * @createDate 2022-05-19 16:13:06
 * @Entity top.thegodisgad.school.entity.Student
 */
@Repository
@Mapper
public interface StudentMapper {
    int insert(Student student);

    int delete(Student student);

    List<Student> findAllByUserId(@Param(value = "userId") Long userId);

    List<Student> findAllBy(Student student);

    List<Student> findAllBySchoolId(@Param(value = "schoolId") Long schoolId);

    Student findByStudentIdStudent(@Param(value = "studentId") Long studentId);
}




