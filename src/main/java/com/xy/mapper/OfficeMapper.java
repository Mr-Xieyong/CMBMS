package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.entity.dos.Office;
import org.apache.ibatis.annotations.Param;

/**
 * @author Xieyong
 * @date 2020/1/14 - 17:13
 */
public interface OfficeMapper  extends BaseMapper<Office> {

    //根据组织名判断该组织是否存在
    int isExistByOfficeName(@Param("officeName") String officeName);
}
