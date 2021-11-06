package passta.paas_ta_back.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import passta.paas_ta_back.domain.OrderItem;
import passta.paas_ta_back.domain.Shop;
import passta.paas_ta_back.repository.item.ItemRegisterDto;
import passta.paas_ta_back.repository.order.OrderRepository;
import passta.paas_ta_back.repository.orderItem.OrderItemRepository;
import passta.paas_ta_back.repository.shop.RegisterDto;
import passta.paas_ta_back.web.file.FileStore;

import java.io.IOException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
}