package service.payment_processors;

public interface PaymentProcessor<T> {

    boolean processPayment(T amount) throws Exception;

    String getProcessorType();
}