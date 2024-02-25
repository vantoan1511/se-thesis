package com.newswebsite.main.api.admin;

import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.session.ISessionService;
import com.newswebsite.main.service.userservice.IUserWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPI {

    private final IUserWriter userWriter;

    private final ISessionService sessionService;

    @Autowired
    public UserAPI(IUserWriter userWriter, ISessionService sessionService) {
        this.userWriter = userWriter;
        this.sessionService = sessionService;
    }

    @PutMapping("/{username}/grant")
    public ResponseEntity<?> grantPrivileges(@PathVariable("username") String username,
                                             @RequestBody List<String> roles) {
        userWriter.grant(username, roles);
        sessionService.expireByUsername(username);
        return ResponseEntity.ok(new SuccessResponse(new Date(), HttpStatus.OK, "Phân vai trò thành công ", roles));
    }
}
