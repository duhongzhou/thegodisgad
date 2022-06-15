package top.thegodisgad.school.service;

import top.thegodisgad.school.entity.Professional;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_professional(专业)】的数据库操作Service
* @createDate 2022-05-19 16:13:21
*/
public interface ProfessionalService  {
    public Long add(Professional professional);
    public boolean update(Professional professional);

    public Professional findByProfessionalId(Professional professional);
    public Professional findByProfessionalId(Long professionalId);
    public List<Professional> findByProfessionalIds(List<Long> professionalIds);
    public List<Professional> find();


}
