//package com.wei.user.api;
//
//
//import com.wei.common.result.Result;
//import com.wei.model.user.UserInfo;
//import com.wei.user.service.UserInfoService;
//import com.wei.vo.user.LoginVo;
//import com.wei.vo.user.MailLoginVo;
//import com.wei.vo.user.UserAuthVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserInfoApiController {
//
//    @Autowired
//    private UserInfoService userInfoService;
//
//    //用户手机号登录接口
//    @PostMapping("login")
//    public Result login(@RequestBody LoginVo loginVo) {
//        Map<String,Object> info = userInfoService.loginUser(loginVo);
//        return Result.ok(info);
//    }
//
//    //用户邮箱登录接口
//    @PostMapping("mailLogin")
//    public Result mailLogin(@RequestBody MailLoginVo loginVo) {
//        Map<String,Object> info = userInfoService.mailLoginUser(loginVo);
//        return Result.ok(info);
//    }
//
//    //用户认证接口
//    @PostMapping("auth/userAuth")
//    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
//        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
//        userInfoService.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
//        return Result.ok();
//    }
//
//    //获取用户id信息接口
//    @GetMapping("auth/getUserInfo")
//    public Result getUserInfo(HttpServletRequest request) {
//        Long userId = AuthContextHolder.getUserId(request);
//        UserInfo userInfo = userInfoService.getById(userId);
//        return Result.ok(userInfo);
//    }
//}