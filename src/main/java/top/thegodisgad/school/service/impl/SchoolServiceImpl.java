package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.school.entity.School;
import top.thegodisgad.school.mapper.SchoolMapper;
import top.thegodisgad.school.service.SchoolService;
import top.thegodisgad.utils.IDUtils;
import top.thegodisgad.utils.PinyinUtils;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_school(学校)】的数据库操作Service实现
 * @createDate 2022-05-19 16:13:14
 */
@Service
public class SchoolServiceImpl
        implements SchoolService {
    SchoolMapper schoolMapper;

    @Autowired
    public SchoolServiceImpl(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(School school) {
        Long id = IDUtils.createId();
        StringBuilder firstCapitalLetter = PinyinUtils.getFirstCapitalLetter(school.getSchoolName());
        school.setSchoolPrefix(String.valueOf(firstCapitalLetter));
        school.setSchoolId(id);
        int insert = schoolMapper.insert(school);

        return insert == 1?id:null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long schoolId) {
        int delete = schoolMapper.delete(schoolId);
        return delete == 1;
    }

    @Override
    public boolean update(School school) {
        int update = schoolMapper.update(school);
        return update == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public School findBySchoolId(Long schoolId) {
        return schoolMapper.selectBySchoolId(schoolId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<School> findSchool(School school) {

        return schoolMapper.select(school);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long findNullAndCreate(School school) {
        List<School> school1 = findSchool(school);
        Long id = null;
        if (school == null) {

            assert school != null;
            id= add(school);
        }
        return id;
    }

    /**
     * @return
     */
    @Override
    public List<School> findAll() {
        return schoolMapper.selectAll();
    }
}




