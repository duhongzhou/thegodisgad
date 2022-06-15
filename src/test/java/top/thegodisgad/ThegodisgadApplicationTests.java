package top.thegodisgad;


import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.thegodisgad.school.entity.School;
import top.thegodisgad.school.mapper.SchoolMapper;
import top.thegodisgad.utils.PinyinUtils;
import top.thegodisgad.utils.token.RedisOperator;

import java.util.List;

@SpringBootTest

class ThegodisgadApplicationTests {
    @Test
    void contextLoads() throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(PinyinUtils.getFirstCapitalLetter("杜洪洲"));

    }
    @Autowired
    SchoolMapper schoolMapper;
    @Test
    void sch(){

        List<School> schools = schoolMapper.selectAll();

        schools.forEach(school -> {
            school.setSchoolPrefix(String.valueOf(PinyinUtils.getFirstCapitalLetter(school.getSchoolName())));
            schoolMapper.update(school);
        });
    }
    @Autowired
    RedisOperator redisOperatorp;
    @Test
    void redis(){
    redisOperatorp.set("123","123",10000);
        System.out.println(redisOperatorp.get("123"));
    }
}
