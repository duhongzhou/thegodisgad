package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.entity.OpeningProfessional;
import top.thegodisgad.school.entity.Professional;
import top.thegodisgad.school.mapper.OpeningProfessionalMapper;
import top.thegodisgad.school.service.AcademyService;
import top.thegodisgad.school.service.OpeningProfessionalService;
import top.thegodisgad.school.service.ProfessionalService;
import top.thegodisgad.utils.IDUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yhdhz
 * @description 针对表【tb_opening_professional(开设专业)】的数据库操作Service实现
 * @createDate 2022-05-19 16:13:27
 */
@Service
public class OpeningProfessionalServiceImpl
        implements OpeningProfessionalService {
    AcademyService academyService;
    ProfessionalService professionalService;
    OpeningProfessionalMapper openingProfessionalMapper;

    @Autowired
    public OpeningProfessionalServiceImpl(AcademyService academyService, ProfessionalService professionalService, OpeningProfessionalMapper openingProfessionalMapper) {
        this.academyService = academyService;
        this.professionalService = professionalService;
        this.openingProfessionalMapper = openingProfessionalMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(OpeningProfessional openingProfessional) {
        Long id = IDUtils.createId();
        openingProfessional.setOpeningProfessionalId(id);
        int insert = openingProfessionalMapper.insert(openingProfessional);
        return insert == 1 ? id : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(OpeningProfessional openingProfessional) {
        int delete = openingProfessionalMapper.delete(openingProfessional);
        return delete == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Professional> findAcademyProfessional(Long academyId) {
        List<OpeningProfessional> openingProfessionals = openingProfessionalMapper.selectByAcademyId(academyId);
        if (openingProfessionals.size() >= 1) {
            List<Long> professionalIds =
                    openingProfessionals.stream().map(OpeningProfessional::getProfessionalId).collect(Collectors.toList());
            List<Professional> byProfessionalIds = professionalService.findByProfessionalIds(professionalIds);
            return byProfessionalIds;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Academy> findProfessionalAcademy(Long professionalId) {
        List<OpeningProfessional> openingProfessionals = openingProfessionalMapper.selectByProfessionalId(professionalId);
        if (openingProfessionals.size() >= 1) {
            List<Long> academyIds =
                    openingProfessionals.stream().map(OpeningProfessional::getAcademyId).collect(Collectors.toList());
            List<Academy> academys = academyService.findAcademyIds(academyIds);
            return academys;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Academy> findAcademyBySchoolId(Long schoolId) {
        List<Academy> academies = academyService.findAcademyBySchoolId(schoolId);
        if (academies != null || academies.size() != 0) {
            for (int i = 0; i < academies.size(); i++) {
                academies.get(i).setProfessionals(findAcademyProfessional(academies.get(i).getAcademyId()));
            }
        }
        return academies;
    }
}




