package top.thegodisgad.address.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.address.entity.Address;
import top.thegodisgad.address.mapper.AddressMapper;
import top.thegodisgad.address.service.AddressService;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_address(地址)】的数据库操作Service实现
 * @createDate 2022-05-19 16:18:00
 */
@Service
public class AddressServiceImpl
        implements AddressService {
    AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    /**
     *
     */
    @Override
    public boolean add(Address address) {
        if (addressMapper.selectAll(address) == null) {

            return addressMapper.insertAll(address) == 1;
        } else {
            throw new UserException(UserExceptionEnum.UPDATE_ADDRESS_EXISTENCE);
        }
    }

    /**
     *
     */
    @Override
    public int delete(Address address) {

        return addressMapper.delete(address);
    }

    /**
     *
     */
    @Override
    public List<Address> search(Long userId) {

        return addressMapper.selectByUserIdAddresses(userId);
    }
}




