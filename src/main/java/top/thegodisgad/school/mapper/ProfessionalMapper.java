package top.thegodisgad.school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.school.entity.Professional;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_professional(专业)】的数据库操作Mapper
* @createDate 2022-05-19 16:13:21
* @Entity top.thegodisgad.school.entity.Professional
*/
@Repository
@Mapper
public interface ProfessionalMapper  {
    int insert(Professional professional);
    int update(Professional professional);

    int deleteByProfessionalId(@Param(value = "professionalId") Long professionalId);
    Professional selectAllByProfessionalId(@Param(value = "professionalId") Long professionalId);
    List<Professional> selectByProfessionalIds(List<Long> professionalIds);

    List<Professional> selectAll();
}




