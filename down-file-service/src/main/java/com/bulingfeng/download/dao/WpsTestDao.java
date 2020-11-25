package com.bulingfeng.download.dao;

import com.bulingfeng.download.model.WpsTestPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-25
 */
public interface WpsTestDao {
    /**
     * 查询数据根据类型和数量限制
     * @param type
     * @param limit
     * @return
     */
    List<WpsTestPo> selectByType(@Param("type") String type,@Param("limit")Integer limit);
}
