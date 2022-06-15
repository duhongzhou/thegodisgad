package top.thegodisgad.address.service;

import top.thegodisgad.address.entity.Address;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_address(地址)】的数据库操作Service
* @createDate 2022-05-19 16:18:00
*/
public interface AddressService  {

        public boolean add(Address address);

        public int delete(Address address);


        public List<Address> search(Long userId);
}
