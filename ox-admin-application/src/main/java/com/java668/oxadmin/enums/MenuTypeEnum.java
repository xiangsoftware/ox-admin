package com.java668.oxadmin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements ICommonEnum<Integer>  {

    DIRECTORY(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    ;

    private Integer code;

    private String remark;
}
