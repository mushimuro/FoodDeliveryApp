package com.sparta.fooddeliveryapp.domain.order.service;

import com.sparta.fooddeliveryapp.domain.cart.entity.Cart;
import com.sparta.fooddeliveryapp.domain.cart.entity.CartDetail;
import com.sparta.fooddeliveryapp.domain.cart.repository.CartDetailRepository;
import com.sparta.fooddeliveryapp.domain.cart.repository.CartRepository;
import com.sparta.fooddeliveryapp.domain.menu.entity.Menu;
import com.sparta.fooddeliveryapp.domain.menu.repository.MenuRepository;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderDetailRequestDto;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderDetailResponseDto;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderRequestDto;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderResponseDto;
import com.sparta.fooddeliveryapp.domain.order.entity.OrderDetail;
import com.sparta.fooddeliveryapp.domain.order.entity.Orders;
import com.sparta.fooddeliveryapp.domain.order.repository.OrderDetailRepository;
import com.sparta.fooddeliveryapp.domain.order.repository.OrderRepository;
import com.sparta.fooddeliveryapp.domain.store.entity.Store;
import com.sparta.fooddeliveryapp.domain.user.entity.User;
import com.sparta.fooddeliveryapp.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    @Transactional
    public void order(User user, Long cartId) {
        //유저 조회
        // 주문메뉴 리스트
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new NullPointerException("없는 장바구니입니다")
        );
        List<CartDetail> cartDetailList = cartDetailRepository.findAllByCart(cart).orElseThrow(
                // 장바구니(cart)에 메뉴(cartDetail)가 없는 경우
                () -> new NullPointerException("장바구니가 비어있습니다")
        );
        Integer totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getMenu().getPrice();
        }
        Orders orders = new Orders(
                cart.getStore(),
                user,
                totalPrice
        );
        log.info("오더 아이디 DB 저장");
        orderRepository.save(orders);
        log.info("저장 완료");
        // 주문 목록도 저장
        for (CartDetail cartDetail : cartDetailList) {
            OrderDetail orderDetail = new OrderDetail(orders, cartDetail.getMenu(), 1);
            orderDetailRepository.save(orderDetail);
        }
    }

    public List<OrderResponseDto> getOrderList(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Orders> ordersPage = orderRepository.findAllByUserOrderByCreatedAtDesc(user, pageable);

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for (Orders orders : ordersPage) {
            //이 주문번호에 해당하는 주문상세 리스트 조회하는 메서드가 필요하다.
            List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrders(orders);

            List<OrderDetailResponseDto> orderDetailResponseDtoList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetailResponseDtoList.add(new OrderDetailResponseDto(orderDetail));
            }
            orderResponseDtoList.add(new OrderResponseDto(orders, orderDetailResponseDtoList));
        }

        return orderResponseDtoList;

    }
}
