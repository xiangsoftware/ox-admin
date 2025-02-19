package com.java668.oxadmin.modules.system.controller;

import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.modules.system.dto.request.UserPageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserPassReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserReqDTO;
import com.java668.oxadmin.modules.system.dto.request.groups.Insert;
import com.java668.oxadmin.modules.system.dto.request.groups.Update;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;
import com.java668.oxadmin.modules.system.service.UserService;
import com.mzt.logapi.starter.annotation.LogRecord;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc 用户管理
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 新增用户
     *
     * @param body
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "新增用户",
            type = "用户管理",
            bizNo = ""
    )
    @PostMapping
    @ApiOperation("新增用户")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<Integer> add(@RequestBody @Validated(Insert.class) UserReqDTO body) {
        return R.success(userService.add(body));
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "删除用户",
            type = "用户管理",
            bizNo = ""
    )
    @DeleteMapping
    @ApiOperation("删除用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Integer> delete(@RequestParam(name = "ids") List<Long> ids) {
        return R.success(userService.delete(ids));
    }

    /**
     * 更新用户
     *
     * @param body
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "更新用户",
            type = "用户管理",
            bizNo = ""
    )
    @PatchMapping
    @ApiOperation("更新用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Integer> update(@RequestBody @Validated(Update.class) UserReqDTO body) {
        return R.success(userService.update(body));
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "查询用户详情",
            type = "用户管理",
            bizNo = ""
    )
    @GetMapping("{id}")
    @ApiOperation("查询用户详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<UserRespDTO> get(@PathVariable("id") Long id) {
        return R.success(userService.get(id));
    }

    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "分页查询用户列表",
            type = "用户管理",
            bizNo = ""
    )
    @GetMapping("/page")
    @ApiOperation("分页查询用户列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<PageResult<UserRespDTO>> page(UserPageReqDTO params) {
        return R.success(userService.page(params));
    }

    /**
     * 修改用户状态
     *
     * @param status
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "修改用户状态",
            type = "用户管理",
            bizNo = ""
    )
    @ApiOperation("修改用户状态")
    @PatchMapping("/{userId}/changeStatus")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Integer> changeStatus(@PathVariable(name = "userId") Long userId,
                                   @RequestParam(name = "status") Integer status) {
        return R.success(userService.changeStatus(userId, status));
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @LogRecord(
            fail = "{{#_errorMsg}}",
            success = "{{#_ret}}",
            subType = "修改密码",
            type = "用户管理",
            bizNo = ""
    )
    @ApiOperation("修改密码")
    @PatchMapping(value = "/modifyPass")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Integer> modifyPass(@RequestBody @Validated UserPassReqDTO dto) {
        return R.success(userService.modifyPass(dto));
    }

    /**
     * 测试接口
     *
     * @param body
     * @return
     */
    @PostMapping("/test")
    @ApiOperation("测试接口")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Boolean> test(@RequestBody @Validated UserReqDTO body) {
        return R.success(Boolean.FALSE);
    }

}