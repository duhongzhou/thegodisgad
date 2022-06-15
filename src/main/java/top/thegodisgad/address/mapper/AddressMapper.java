package top.thegodisgad.address.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.address.entity.Address;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_address(地址)】的数据库操作Mapper
 * @createDate 2022-05-19 16:18:00
 * @Entity top.thegodisgad.address.entity.Address
 */
@Repository
@Mapper
public interface AddressMapper {
    int insertAll(Address address);

    List<Address> selectByUserIdAddresses(@Param(value = "userId") Long userId);

    Address selectAll(Address address);

    int delete(Address address);
}




