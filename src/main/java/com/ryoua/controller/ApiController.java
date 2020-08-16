package com.ryoua.controller;

import com.ryoua.handler.UserLocal;
import com.ryoua.model.ApiInfo;
import com.ryoua.model.Contact;
import com.ryoua.model.common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/14
 **/
@RestController
@Slf4j
public class ApiController extends BaseController {
//    @GetMapping("/api")
//    @ApiOperation(value = "获取全部接口", notes = "获取全部接口", httpMethod = "GET")
//    public Result findApi() {
////        List<ApiInfo> list = .findContactByUser(UserLocal.getCurrentUserId());
////        return Result.SUCCESS(list);
//    }

    @PutMapping("/api")
    @ApiOperation(value = "更新接口", notes = "更新接口", httpMethod = "PUT")
    public Result updateApi(@RequestBody Contact contact) {
        Integer flag = contactService.updateUserContact(contact.getId(), contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }

    @DeleteMapping("/api/{id}")
    @ApiOperation(value = "删除接口", notes = "删除接口", httpMethod = "DELETE")
    public Result deleteApi(@PathVariable("id") Integer id) {
        Integer flag = contactService.deleteUserContact(id);
        return resultByFlag(flag);
    }

    @PostMapping("/api")
    @ApiOperation(value = "添加接口", notes = "添加接口", httpMethod = "POST")
    public Result insertApi(@RequestBody Contact contact) {
        Integer flag = contactService.insertUserContact(UserLocal.getCurrentUserId(), contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }
}
