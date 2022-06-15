package top.thegodisgad.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.entity.Professional;
import top.thegodisgad.school.entity.School;
import top.thegodisgad.school.entity.Student;
import top.thegodisgad.school.mapper.StudentMapper;
import top.thegodisgad.school.service.AcademyService;
import top.thegodisgad.school.service.ProfessionalService;
import top.thegodisgad.school.service.SchoolService;
import top.thegodisgad.school.service.StudentService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.user.service.UserInformationLogService;
import top.thegodisgad.utils.IDUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yhdhz
 * @description 针对表【tb_student(就读于)】的数据库操作Service实现
 * @createDate 2022-05-19 16:13:06
 */
@Service
public class StudentServiceImpl
        implements StudentService {

    UserInformationLogService userInformationLogService;
    StudentMapper studentMapper;
    SchoolService schoolService;
    AcademyService academyService;

    ProfessionalService professionalService;

    @Autowired
    public StudentServiceImpl(UserInformationLogService userInformationLogService, StudentMapper studentMapper, SchoolService schoolService, AcademyService academyService, ProfessionalService professionalService) {
        this.userInformationLogService = userInformationLogService;
        this.studentMapper = studentMapper;
        this.schoolService = schoolService;
        this.academyService = academyService;
        this.professionalService = professionalService;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(Student student) {
        Long id = IDUtils.createId();
        student.setStudentId(id);

        if (student.getAcademyId() == null) {
            Academy academy = new Academy();
            academy.setSchoolId(student.getSchoolId());
            academy.setAcademyName(student.getAcademyName());
            Long nullAndCreate = academyService.findNullAndCreate(academy);
            student.setAcademyId(nullAndCreate);
        }
        int insert = studentMapper.insert(student);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> addStudents(List<Student> students) {
        School bySchoolId = schoolService.findBySchoolId(students.get(0).getSchoolId());
        String schoolPrefix = bySchoolId.getSchoolPrefix();
        //注册学生
        List<UserInformationLog> userInformationLogs = students.stream().map(student -> {
            String l = student.getStudentId() + schoolPrefix;
            UserInformationLog userInformationLog = student.getUserInformationLog();
            userInformationLog.setUserName(l);
            return userInformationLog;
        }).collect(Collectors.toList());
        List<User> users = userInformationLogService.batchRegistered(userInformationLogs);
        int insert = 0;
        //成功在学生表中插入学生
        if (users.size() != students.size()) {
            throw new RuntimeException();
        } else {
            for (int i = 0; i < students.size(); i++) {
                students.get(i).setUserId(users.get(i).getUserId());
                insert += studentMapper.insert(students.get(i));
            }
        }

        List<Long> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
        return userIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Student student) {
        Student byStudentIdStudent = studentMapper.findByStudentIdStudent(student.getStudentId());
        userInformationLogService.registeredOut(byStudentIdStudent.getUserId());
        return studentMapper.delete(student) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Student> findByUserId(Long userId) {
        return studentMapper.findAllByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Student> find(Student student) {
        return studentMapper.findAllBy(student);
    }

    /**
     * @param schoolId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Student> findBySchoolId(Long schoolId) {
        List<Student> allBySchoolId = studentMapper.findAllBySchoolId(schoolId);
        List<Student> studentsNameAndCertificate = userInformationLogService.findStudentsNameAndCertificate(allBySchoolId);
        studentsNameAndCertificate.forEach(student -> {
            if (student.getAcademyId() != null) {
                student.setAcademyName(academyService.findAcademyByAcademyId(student.getAcademyId()).getAcademyName());
            } else {
                student.setAcademyName("未验证的学生");
            }
            Professional byProfessionalId = professionalService.findByProfessionalId(student.getProfessionalId());
            student.setProfessionalName(byProfessionalId.getProfessionalName());
        });
        return studentsNameAndCertificate;

    }
}




