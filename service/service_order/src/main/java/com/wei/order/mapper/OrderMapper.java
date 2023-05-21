package com.wei.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.model.order.OrderInfo;
import com.wei.vo.order.OrderCountQueryVo;
import com.wei.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;


public interface OrderMapper extends BaseMapper<OrderInfo> {

    //查询预约统计数据的方法
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}
