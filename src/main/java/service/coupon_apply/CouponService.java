package service.coupon_apply;

import java.math.BigDecimal;

public interface CouponService {

    BigDecimal applyCoupon(BigDecimal price, String couponCode);
}
