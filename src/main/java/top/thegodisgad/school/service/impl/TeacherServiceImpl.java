package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.school.entity.Teacher;
import top.thegodisgad.school.mapper.TeacherMapper;
import top.thegodisgad.school.service.TeacherService;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_teacher(就读于)】的数据库操作Service实现
 * @createDate 2022-05-19 13:37:31
 */
@Service
public class TeacherServiceImpl
        implements TeacherService {

    TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Teacher teacher) {
        return teacherMapper.insert(teacher) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Teacher teacher) {
        return teacherMapper.delete(teacher) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Teacher> find(Teacher teacher) {
        return teacherMapper.findAllBy(teacher);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Teacher findByUserId(Long userId) {
        return teacherMapper.findAllByUserId(userId);
    }
}




