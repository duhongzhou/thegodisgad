package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.Teacher;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_teacher(就读于)】的数据库操作Service
 * @createDate 2022-05-19 13:37:31
 */
public interface TeacherService {

    public boolean add(Teacher teacher);

    public boolean delete(Teacher teacher);

    public List<Teacher> find(Teacher teacher);

    public Teacher findByUserId(Long userId);
}
