package service.payment_processors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class StripePaymentProcessor implements PaymentProcessor<Float> {

    private static final Float MAX_PRICE = 100f;
    private static final String PROCESSOR_TYPE = "stripe";

    @Override
    public boolean processPayment(Float price) {
        if (price < MAX_PRICE) {
            log.error("Платеж через Stripe не может быть обработан: сумма меньше минимально допустимой. Сумма: {}", price);
            return false;
        }
        log.info("Платеж через Stripe на сумму: {} успешно обработан.", price);
        return true;
    }

    @Override
    public String getProcessorType() {
        return PROCESSOR_TYPE;
    }
}