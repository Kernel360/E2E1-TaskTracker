package org.fastcampus.proejct.admin.controller;

import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/api")
public class adminRestController {

    @Autowired
    private UserInfoService userInfoService;
    @PutMapping(value = "/updateIsBan")
    public ResponseEntity<?> updateIsBan(@RequestBody Map<String,Object> data) {
        userInfoService.updateIsBan(data);
        return ResponseEntity.ok("Data updated successfully");
    }

    @PutMapping(value = "/updateAdminCheck")
    public ResponseEntity<?> updateAdminCheck(@RequestBody Map<String,Object> data) {
        userInfoService.updateAdminCheck(data);
        return ResponseEntity.ok("Data updated successfully");
    }
}
