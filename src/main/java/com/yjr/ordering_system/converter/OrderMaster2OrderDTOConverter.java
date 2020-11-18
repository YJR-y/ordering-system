package com.yjr.ordering_system.converter;

import com.yjr.ordering_system.dto.OrderDTO;
import com.yjr.ordering_system.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yjr
 * @since 2020/11/12 15:16
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map((OrderMaster e) -> {
            return convert(e);
        }).collect(Collectors.toList());
    }
}
