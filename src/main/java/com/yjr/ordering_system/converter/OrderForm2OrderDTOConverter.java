package com.yjr.ordering_system.converter;

import com.yjr.ordering_system.dto.OrderDTO;
import com.yjr.ordering_system.entity.OrderDetail;
import com.yjr.ordering_system.form.CartItemForm;
import com.yjr.ordering_system.form.OrderForm;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yjr
 * @since 2020/11/18 下午2:16
 */
public class OrderForm2OrderDTOConverter {

    public static OrderDTO orderFormToOrderDTO(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setOrderId(orderForm.getOpenId());

        List<CartItemForm> cartItemFormList = orderForm.getItems();
        // cartItemFormList -> List<OrderDetail>
        List<OrderDetail> orderDetails = cartItemFormList.stream().map((CartItemForm cartItemForm) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(cartItemForm.getProductId());
            orderDetail.setProductQuantity(cartItemForm.getProductQuantity());
            return orderDetail;
        }).collect(Collectors.toList());
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

}
