package passta.paas_ta_back;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import passta.paas_ta_back.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            //초기 admin 세팅
            User admin = User.createUser("admin", "admin", "admin", "admin", UserType.ADMIN);
            em.persist(admin);
            //초기 사용자 세팅
            User user2 = User.createUser("이름2", "email2", "1234", "주소1", UserType.CONSUMER);
            em.persist(user2);
            // 초기 가게 사용자 세팅
            User user = User.createUser("이름3", "email3", "1234", "주소1", UserType.SELLER);
            em.persist(user);

            Land landInit = null;
            for (int y = 250; y <= 650; y += 400) {
                for (int x = 200; x <= 1700; x += 600) {
                    //초기 Land 좌표 셋팅값
                    LandLocations landLocations = new LandLocations(x, y, x + 300, y, x, y + 200, x + 300, y + 200);
                    //초기 Land셋팅
                    landInit = Land.createLand("성북", landLocations);
                    em.persist(landInit);
                }
            }


            // 초기 가게 세팅 //마지막 위치가 세팅됨
            UploadFile uploadFile1 = new UploadFile("사용자파일명1", "uuid파일명1");
            Shop shop1 = Shop.createShop(
                    user,
                    "사업자등록번호1",
                    "가게1",
                    "가게전화번호1",
                    "가게지역명1",
                    "가게상세주소1",
                    "가게 타입1",
                    uploadFile1,
                    landInit);
            em.persist(shop1);

//            UploadFile uploadFile2 = new UploadFile("사용자파일명2", "uuid파일명2");
//            Shop shop2 = Shop.createShop(
//                    user,
//                    "사업자등록번호2",
//                    "가게2",
//                    "가게전화번호2",
//                    "가게지역명2",
//                    "가게상세주소2",
//                    "가게 타입2",
//                    uploadFile2);
//            em.persist(shop2);

            // 초기 image list 세팅
            List<UploadFile> uploadFiles = new ArrayList<>();
            UploadFile itemImage = new UploadFile("사용자파일명3", "uuid파일명3");
            for (int i = 0; i < 3; i++) {
                uploadFiles.add(itemImage);
            }

            // 초기 item 세팅
            Item item1 = Item.createItem(
                    shop1,
                    "상품1",
                    "상품 내용",
                    10000,
                    10,
                    uploadFiles);
            em.persist(item1);

            Item item2 = Item.createItem(
                    shop1,
                    "상품2",
                    "상품 내용",
                    20000,
                    20,
                    uploadFiles);
            em.persist(item2);

            Item item3 = Item.createItem(
                    shop1,
                    "상품3",
                    "상품 내용",
                    30000,
                    30,
                    uploadFiles);
            em.persist(item3);

            // 주문 finish 된 상태
            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem1 = OrderItem.createOrderItem(item1, item1.getPrice() * 2, 2);
            em.persist(orderItem1);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, item2.getPrice() * 2, 2);
            em.persist(orderItem2);
            OrderItem orderItem3 = OrderItem.createOrderItem(item3, item3.getPrice() * 5, 5);
            em.persist(orderItem3);
            orderItems.add(orderItem1);
            orderItems.add(orderItem2);
            orderItems.add(orderItem3);
            Order order = Order.createOrder(user, orderItems);
            order.finishOrder();
            em.persist(order);
        }
    }
}
