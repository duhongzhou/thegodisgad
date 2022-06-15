package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.OpeningProfessional;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_opening_professional(开设专业)】的数据库操作Mapper
* @createDate 2022-05-19 16:13:27
* @Entity top.thegodisgad.school.entity.OpeningProfessional
*/
@Repository
@Mapper
public interface OpeningProfessionalMapper  {
    int insert(OpeningProfessional openingProfessional);
    int delete(OpeningProfessional openingProfessional);

    List<OpeningProfessional> selectByAcademyId(@Param(value = "academyId") Long academyId);
    List<OpeningProfessional> selectByProfessionalId(@Param(value = "professionalId") Long professionalId);

}




