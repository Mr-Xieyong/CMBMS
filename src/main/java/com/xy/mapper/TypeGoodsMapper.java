package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.entity.dos.Goods;
import com.xy.entity.dos.TypeGoods;
import com.xy.entity.dtos.GoodsDto;
import com.xy.entity.dtos.TypeGoodsDto;
import com.xy.entity.vos.TypeGoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Xieyong
 * @date 2019/12/19 - 15:16
 */
@Repository
public interface TypeGoodsMapper extends BaseMapper<TypeGoods> {

    /**
     * 判断当前机构组织是否有此类别物资
     */
    TypeGoods hasTypeGoods(@Param("officeId") Integer officeId, @Param("typeName") String typeName);


    /**
     * 删除当前机构的该类别物资
     */

    Integer deleteTypeGoods(@Param("userId") Integer userId,
                            @Param("updateDate") Date updateDate,
                            @Param("officeId") Integer officeId,
                            @Param("id") Integer id);

    List<GoodsDto> selectGoodsByTypeId(@Param("typeId") Integer typeId);

    /**
     * 模糊查询物资
     *
     * @param goodsName
     * @return
     */
    List<TypeGoodsDto> queryTypeGoods(@Param("goodsName") String goodsName);
}

