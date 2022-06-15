package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.Student;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_student(就读于)】的数据库操作Service
 * @createDate 2022-05-19 16:13:06
 */
public interface StudentService {

    public Long add(Student student);

    public List<Long> addStudents(List<Student> students);

    public boolean delete(Student student);

    public List<Student> findByUserId(Long userId);

    public List<Student> find(Student student);


    public List<Student> findBySchoolId(Long schoolId);


}
