package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.School;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_school(学校)】的数据库操作Service
* @createDate 2022-05-19 16:13:14
*/
public interface SchoolService  {
    Long add(School school);

    boolean delete(Long schoolId);
    boolean update(School school);

    School findBySchoolId(Long schoolId);

    List<School> findSchool(School school);

    Long findNullAndCreate(School school);

    List<School> findAll();


}
