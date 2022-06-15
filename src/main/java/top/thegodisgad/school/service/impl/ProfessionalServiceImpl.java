package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.school.entity.Professional;
import top.thegodisgad.school.mapper.ProfessionalMapper;
import top.thegodisgad.school.service.ProfessionalService;
import top.thegodisgad.utils.IDUtils;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_professional(专业)】的数据库操作Service实现
* @createDate 2022-05-19 16:13:21
*/
@Service
public class ProfessionalServiceImpl 
    implements ProfessionalService{
    ProfessionalMapper professionalMapper;
    @Autowired
    public ProfessionalServiceImpl(ProfessionalMapper professionalMapper) {
        this.professionalMapper = professionalMapper;
    }

    @Override
    public Long add(Professional professional) {
        Long id = IDUtils.createId();
        professional.setProfessionalId(id);
        int insert = professionalMapper.insert(professional);
        return insert==1?id:null;
    }

    @Override
    public boolean update(Professional professional) {

        return professionalMapper.update(professional)==1;
    }

    @Override
    public Professional findByProfessionalId(Professional professional) {
        return findByProfessionalId(professional.getProfessionalId());
    }

    @Override
    public Professional findByProfessionalId(Long professionalId) {
        return professionalMapper.selectAllByProfessionalId(professionalId);
    }

    @Override
    public List<Professional> findByProfessionalIds(List<Long> professionalIds) {
        return professionalMapper.selectByProfessionalIds(professionalIds);
    }

    @Override
    public List<Professional> find() {
        return professionalMapper.selectAll();
    }

}




