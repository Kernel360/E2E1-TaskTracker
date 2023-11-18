package org.fastcampus.proejct.admin.controller;

import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/api")
public class adminRestController {

    @Autowired
    private UserInfoService userInfoService;
    @PutMapping(value = "/updateIsBan")
    public ResponseEntity<?> updateIsBan(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Map<String,Object> data) {
        int result = userInfoService.updateIsBan(data);
        return ResponseEntity.ok("Data updated successfully");
    }
}
