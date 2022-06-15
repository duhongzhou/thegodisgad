package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.Academy;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_academy(学院)】的数据库操作Service
 * @createDate 2022-05-19 16:13:33
 */
public interface AcademyService {

    public Long add(Academy academy);

    public boolean delete(Long academyId);


    public boolean update(Academy academy);


    public List<Academy> findAcademyIds(List<Long> academyIds);

    public List<Academy> findAcademy(Academy academy);

    public Long findNullAndCreate(Academy academy);

    public List<Academy> findAcademyBySchoolId(Long schoolId);

    Academy findAcademyByAcademyId(Long academyId);
}
