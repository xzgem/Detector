package com.ryoua.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryoua.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Service
public class ContactService extends BaseService {
    public Integer deleteUserContact(Integer id) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setValid(0);
        return contactMapper.updateById(contact);
    }

    public Integer updateUserContact(Integer id, String contact, Integer type) {
        Contact contact1 = new Contact();
        contact1.setId(id);
        contact1.setContact(contact);
        contact1.setType(type);
        return contactMapper.updateById(contact1);
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
