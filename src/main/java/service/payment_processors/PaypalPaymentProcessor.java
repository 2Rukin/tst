package service.payment_processors;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaypalPaymentProcessor implements PaymentProcessor<Integer> {

    private static final Integer MAX_PRICE = 100000;
    private static final String PROCESSOR_TYPE = "paypal";


    @Override
    public boolean processPayment(Integer price) throws Exception {
        if (price > MAX_PRICE) {
            log.error("Сумма превышает лимит для Paypal: {}", price);
            return false;
//            throw new Exception("Сумма превышает лимит для Paypal"); можно так и так
        }
        log.info("Платеж через Paypal на сумму: {} успешно проведен", price);
        return true;
    }

    @Override
    public String getProcessorType() {
        return PROCESSOR_TYPE;
    }
}