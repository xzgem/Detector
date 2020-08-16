package com.ryoua.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryoua.model.ApiInfo;
import com.ryoua.model.Contact;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/14
 **/
@Service
public class ApiService extends BaseService {
    public Integer deleteApi(Integer id) {
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setId(id);
        apiInfo.setValid(0);
        return apiInfoMapper.updateById(apiInfo);
    }

    public Integer updateApi(Integer id, ApiInfo apiInfo) {
        return apiInfoMapper.updateById(apiInfo);
    }

    public Integer insertUserContact(Integer user, String contact, Integer type) {
        Contact contact1 = new Contact();
        contact1.setUser(user);
        contact1.setContact(contact);
        contact1.setType(type);
        return contactMapper.insert(contact1);
    }

    public List<Contact> findContactByUser(Integer user) {
        return contactMapper.selectList(new QueryWrapper<Contact>()
                .eq("user", user)
                .eq("valid", 1));
    }

}
