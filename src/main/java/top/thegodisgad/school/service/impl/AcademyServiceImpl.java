package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.mapper.AcademyMapper;
import top.thegodisgad.school.service.AcademyService;
import top.thegodisgad.utils.IDUtils;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_academy(学院)】的数据库操作Service实现
 * @createDate 2022-05-19 16:13:33
 */
@Service
public class AcademyServiceImpl
        implements AcademyService {
    AcademyMapper academyMapper;

    @Autowired
    public AcademyServiceImpl(AcademyMapper academyMapper) {
        this.academyMapper = academyMapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(Academy academy) {
        Long id = IDUtils.createId();
        academy.setAcademyId(id);
        int insert = academyMapper.insert(academy);
        return insert == 1 ? id : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long academyId) {
        return academyMapper.deleteByAcademyId(academyId) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Academy academy) {
        return academyMapper.updateByAcademyId(academy) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Academy> findAcademyIds(List<Long> academyIds) {
        return academyMapper.selectByAcademyIds(academyIds);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Academy> findAcademy(Academy academy) {
        return academyMapper.select(academy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long findNullAndCreate(Academy academy) {
        List<Academy> academy1 = findAcademy(academy);
        Long id = null;
        if (academy1 == null) {
            id = add(academy);
        }
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Academy> findAcademyBySchoolId(Long schoolId) {
        List<Academy> academies = academyMapper.selectBySchoolId(schoolId);
        return academies;
    }

    /**
     * @param academyId
     * @return
     */
    @Override
    public Academy findAcademyByAcademyId(Long academyId) {
        return academyMapper.selectByAcademyId(academyId);
    }
}




