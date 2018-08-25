package lot.service;

import lot.model.*;
import lot.repository.CreditRepository;
import lot.repository.PaymentRepository;
import lot.service.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//todo implement tests

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PaymentServiceTest {

    @MockBean
    private PaymentRepository paymentRepositoryMock;
    @MockBean
    private CreditRepository creditRepositoryMock;

    @Autowired
    private PaymentService paymentService;

    private OffsetDateTime fromDate = OffsetDateTime.of(2018, 8, 1, 10, 0, 0, 0, ZoneOffset.ofHours(0));
    private OffsetDateTime toDate = OffsetDateTime.of(2018, 8, 1, 12, 0, 0, 0, ZoneOffset.ofHours(0));
    private Plate plate = new Plate();
    private LotEntry lotEntry = new LotEntry();
    private Credit credit = new Credit();
    private Payment persistedPayment = new Payment();
    private Long HIGH_CREDIT = 9999L;
    private Long LOW_CREDIT = 1L;
    private long TEST_FARE = 10;
    private ChronoUnit TEST_CHRONO_UNIT = ChronoUnit.MINUTES;

    @Before
    public void setUp() {

        plate.setActive(true);
        plate.setPlate("123-45A");

        User user = new User();
        user.setPlates(new HashSet<>(Arrays.asList(plate)));

        plate.setUser(user);

        lotEntry.setDateFrom(fromDate);
        lotEntry.setDateTo(toDate);
        lotEntry.setPlate(plate);
        lotEntry.setPayment(null);

        credit.setUser(user);
        credit.setValue(0L);

    }


    @Test
    public void processPayment_ReturnsTrue_WhenUserHasFunds() throws Exception {
        credit.setValue(HIGH_CREDIT);
        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        when(paymentRepositoryMock.save(Mockito.any(Payment.class))).thenReturn(persistedPayment);
        when(creditRepositoryMock.findOneByUser(lotEntry.getPlate().getUser())).thenReturn(Optional.ofNullable(credit));

        assertTrue(paymentService.processPayment(lotEntry));

        verify(paymentRepositoryMock, times(1)).save(argumentCaptor.capture());

        assertEquals(lotEntry, argumentCaptor.getValue().getLotEntry());
        Long expectedFare = TEST_FARE * fromDate.until(toDate, TEST_CHRONO_UNIT);
        assertEquals(expectedFare, argumentCaptor.getValue().getValue());
    }

    @Test
    public void processPayment_ReturnsFalse_WhenUserHasNoFunds() throws Exception {
        credit.setValue(LOW_CREDIT);

        when(creditRepositoryMock.findOneByUser(lotEntry.getPlate().getUser())).thenReturn(Optional.ofNullable(credit));

        assertFalse(paymentService.processPayment(lotEntry));

        verify(paymentRepositoryMock, times(0)).save(Mockito.any(Payment.class));

    }

    @Test
    public void processPayment_ReturnsFalse_WhenEntryWasAlreadyPaid() throws Exception {
        lotEntry.setPayment(new Payment());
        assertFalse(paymentService.processPayment(lotEntry));


    }

    @Test
    public void calculatePaymentValue_ReturnsNull_WhenGivenAlreadyPaidEntry() throws Exception {
        lotEntry.setPayment(new Payment());
        assertEquals(null, paymentService.calculatePaymentValue(lotEntry));
    }

    @Test
    public void calculatePaymentValue_ReturnsCorrectValue_WhenGivenUnpaidEntry() throws Exception {
        Long expectedFare = TEST_FARE * fromDate.until(toDate, TEST_CHRONO_UNIT);
        assertEquals(expectedFare, paymentService.calculatePaymentValue(lotEntry).get());


        toDate = fromDate.plus(10, ChronoUnit.MINUTES);
        lotEntry.setDateTo(toDate);
        expectedFare = TEST_FARE * fromDate.until(toDate, TEST_CHRONO_UNIT);
        assertEquals(expectedFare, paymentService.calculatePaymentValue(lotEntry).get());

        toDate = fromDate.plus(10, ChronoUnit.HOURS);
        lotEntry.setDateTo(toDate);
        expectedFare = TEST_FARE * fromDate.until(toDate, TEST_CHRONO_UNIT);
        assertEquals(expectedFare, paymentService.calculatePaymentValue(lotEntry).get());

        toDate = fromDate.plus(10, ChronoUnit.DAYS);
        lotEntry.setDateTo(toDate);
        expectedFare = TEST_FARE * fromDate.until(toDate, TEST_CHRONO_UNIT);
        assertEquals(expectedFare, paymentService.calculatePaymentValue(lotEntry).get());
    }

}