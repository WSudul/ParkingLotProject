package lot.service;

import lot.service.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//todo implement tests

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PaymentServiceTest {
    @Test
    public void processPayment_ReturnsTrue_WhenUserHasFunds() throws Exception {
    }

    @Test
    public void processPayment_ReturnsFalse_WhenUserHasNoFunds() throws Exception {
    }

    @Test
    public void processPayment_ReturnsFalse_WhenEntryWasAlreadyPaid() throws Exception {
    }

    @Test
    public void calculatePaymentValue_ReturnsNull_WhenGivenAlreadyPaidEntry() throws Exception {
    }

    @Test
    public void calculatePaymentValue_ReturnsCorrectValue_WhenGivenUnpaidEntry() throws Exception {
    }

}