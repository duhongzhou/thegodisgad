package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.Academy;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_academy(学院)】的数据库操作Mapper
 * @createDate 2022-05-19 16:13:33
 * @Entity top.thegodisgad.school.entity.Academy
 */
@Repository
@Mapper
public interface AcademyMapper {
    int insert(Academy academy);

    int deleteByAcademyId(@Param(value = "academyId") Long academyId);

    int updateByAcademyId(Academy academy);

    List<Academy> select(Academy academy);

    List<Academy> selectBySchoolId(@Param(value = "schoolId") Long schoolId);

    List<Academy> selectByAcademyIds(List<Long> academyIds);

    Academy selectByAcademyId(@Param(value = "academyId") Long academyId);
}




