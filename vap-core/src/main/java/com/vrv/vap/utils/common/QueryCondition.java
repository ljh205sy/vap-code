package com.vrv.vap.utils.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liujinhui
 * date 2021/3/31 13:40
 */
@ApiModel(
        value = "Query",
        description = "通用列表查询模型"
)
@Data
public class QueryCondition {
    @ApiModelProperty("查询开始条数，默认为 0")
    private int start = 0;
    @ApiModelProperty("查询数量（每页数量），默认为10")
    private int size = 10;
    @ApiModelProperty("查询排序的字段，默认为 空 (不排序)")
    private String order = null;
    @ApiModelProperty("查询排序的值：支持 \"asc\" \"desc\" 默认为空 （不排序）")
    private String by = "asc";


}
