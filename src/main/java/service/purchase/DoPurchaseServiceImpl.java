package service.purchase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.dto.PurchaseRequest;
import model.dto.PurchaseResult;
import org.springframework.stereotype.Service;
import service.coupon_apply.CouponService;
import service.payment_processors.PaymentProcessor;
import service.tax_calculate.TaxService;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class DoPurchaseServiceImpl implements DoPurchaseService {

    private final CouponService couponService;
    private final TaxService taxService;
    private final List<PaymentProcessor> processorsList;

    @Override
    public PurchaseResult doPurchase(PurchaseRequest request) {
        try {
            var paymentProcessor = getPaymentProcessor(request.getPaymentProcessorType());
            var productPrice = getProductPrice(request.getProductId());
            var discount = applyCoupon(productPrice, request.getCouponCode());
            var priceAfterDiscount = productPrice.subtract(discount);
            var totalPrice = calculateTotalPrice(priceAfterDiscount, request.getTaxNumber());
            return processPayment(paymentProcessor, totalPrice);
        } catch (Exception e) {
            log.error("Ошибка при проведении покупки: {}", e.getMessage());
            return PurchaseResult.builder()
                    .isSuccessPurchase(false)
                    .message("Purchase error: " + e.getMessage())
                    .build();
        }
    }

    private PaymentProcessor getPaymentProcessor(String processorType) {
        return processorsList.stream()
                .filter(processor -> processor.getProcessorType().equalsIgnoreCase(processorType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Отсутствует нужный провайдер платежа : " + processorType));
    }

    private BigDecimal getProductPrice(Long productId) {
        switch (productId.intValue()) {
            case 1:
                return BigDecimal.valueOf(100.0); // Iphone
            case 2:
                return BigDecimal.valueOf(20.0);  // Наушники
            case 3:
                return BigDecimal.valueOf(10.0);  // Чехол
            default:
                throw new IllegalArgumentException("Unknown product ID: " + productId);
        }
    }

    private BigDecimal applyCoupon(BigDecimal productPrice, String couponCode) {
        if (nonNull(couponCode)) {
            BigDecimal discount = couponService.applyCoupon(productPrice, couponCode);
            log.info("Применена скидка: {} для купона: {}", discount, couponCode);
            return discount;
        }
        // todo тут логи , метрики
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateTotalPrice(BigDecimal priceAfterDiscount, String taxNumber) {
        BigDecimal taxRate = taxService.calculateTax(taxNumber, priceAfterDiscount);
        return priceAfterDiscount.add(priceAfterDiscount.multiply(taxRate).divide(BigDecimal.valueOf(100)));
    }

    private PurchaseResult processPayment(PaymentProcessor paymentProcessor, BigDecimal totalPrice) {
        try {
            boolean paymentSuccessful = paymentProcessor.processPayment(totalPrice.floatValue());
            if (paymentSuccessful) {
                log.info("Платеж успешно проведен на сумму: {}", totalPrice);
                return PurchaseResult.builder()
                        .isSuccessPurchase(true)
                        .message("Payment successful")
                        .build();
            } else {
                log.error("Платеж не может быть проведен на сумму: {}", totalPrice);
                return PurchaseResult.builder()
                        .isSuccessPurchase(false)
                        .message("Payment faild")
                        .build();
            }
        } catch (Exception e) {
            log.error("Ошибка при проведении платежа: {}", e.getMessage());
            return PurchaseResult.builder()
                    .isSuccessPurchase(true)
                    .message("Payment error: " + e.getMessage())
                    .build();
        }
    }
}