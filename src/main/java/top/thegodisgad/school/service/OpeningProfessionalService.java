package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.entity.OpeningProfessional;
import top.thegodisgad.school.entity.Professional;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_opening_professional(开设专业)】的数据库操作Service
 * @createDate 2022-05-19 16:13:27
 */
public interface OpeningProfessionalService {

    Long add(OpeningProfessional openingProfessional);

    boolean delete(OpeningProfessional openingProfessional);

    /**
     * 寻找学院专业
     *
     * @return java.util.List<top.thegodisgad.school.entity.Professional>
     * @author 杜洪洲
     * @description: findAcademyProfessional
     * @date 2022/5/28 16:39
     */
    public List<Professional> findAcademyProfessional(Long academyId);

    /**
     * 寻找专业学院
     *
     * @return java.util.List<top.thegodisgad.school.entity.Academy>
     * @author 杜洪洲
     * @description: findProfessionalAcademy
     * @date 2022/5/28 16:38
     */
    public List<Academy> findProfessionalAcademy(Long professionalId);

    public List<Academy> findAcademyBySchoolId(Long schoolId);

}
