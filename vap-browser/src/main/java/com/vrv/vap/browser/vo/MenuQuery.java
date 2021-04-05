package com.vrv.vap.browser.vo;

import com.vrv.vap.core.common.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liujinhui
 * date 2021/3/31 13:42
 */
@Data
@NoArgsConstructor
@ApiModel("查询菜单列表")
public class MenuQuery extends Query implements Serializable {

    /**
     * 角色名
     */
    @ApiModelProperty("用户名称，支持模糊匹配")
    @NotNull(message = "用户名称不能为空")
    @NotBlank(message = "用户名称不能为空字符串")
    private String name;

    /**
     * 角色code
     */
    @ApiModelProperty("用户帐号，支持模糊匹配")
    private String code;

    @ApiModelProperty("角色ID，支持多个id，用逗号分开")
    private String id;


    public MenuQuery(String name){
        this.name = name;
    }


}
