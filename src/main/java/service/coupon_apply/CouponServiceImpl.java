package service.coupon_apply;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CouponServiceImpl implements CouponService {

    @Override
    public BigDecimal applyCoupon(BigDecimal price, String couponCode) {
        /*
         Примерная реализация получения скидки по купону
         Я понимаю что хардкод это не комильфо..
         Думаю для собеса хватит.
         */
        switch (couponCode) {
            case "D15":
                return price.multiply(BigDecimal.valueOf(0.15)); // 15% скидка
            case "P10":
                return price.multiply(BigDecimal.valueOf(0.10)); // 10% скидка
            case "P100":
                return price; // 100% скидка
            default:
                return BigDecimal.ZERO; // Нет скидки
        }
    }
}