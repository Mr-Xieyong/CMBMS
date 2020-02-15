package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.entity.dos.Audit;
import org.apache.ibatis.annotations.Param;

/**
 * @author Xieyong
 * @date 2020/2/14 - 13:57
 */
public interface AuditMapper extends BaseMapper<Audit> {

    void updateAuditMsg(@Param("msgId") Integer msgId, @Param("auditResults") Integer auditResults,
                   @Param("auditOpinion") String auditOpinion, @Param("userId") Integer userId);
}
