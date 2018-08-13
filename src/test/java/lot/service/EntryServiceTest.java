package lot.service;

import lot.model.Plate;
import lot.repository.LotEntryRepository;
import lot.service.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EntryServiceTest {

    @MockBean
    private LotEntryRepository lotEntryRepositoryMock;

    private EntryService entryService = new EntryService(lotEntryRepositoryMock);


    private Plate plate;

    @Before
    public void setUp() {
        plate = new Plate();


    }

    @Test
    public void logEntry_ReturnTrue_WhenNewVehicleEnters() throws Exception {

        when(lotEntryRepositoryMock.findOneByPlateAndDateFromIsNull(plate)).thenReturn(Optional.ofNullable(null));
        assertTrue(entryService.logEntry(plate));

    }

    @Test
    public void logEntry_ReturnFalse_WhenVehicleIsInLot() throws Exception {
        assertFalse(entryService.logEntry(plate));
    }

    @Test
    public void logDeparture_ReturnTrue_WhenVehicleLeaves() throws Exception {
        assertTrue(entryService.logDeparture(plate));
    }

    @Test
    public void logDeparture_ReturnFalse_WhenVehicleWasNotInLot() throws Exception {
        assertFalse(entryService.logDeparture(plate));
    }


    @Test
    public void currentLotStatus_ReturnAllPlatesInLot() throws Exception {

        List<Plate> plates = entryService.currentLotStatus();
        assertFalse(plates.isEmpty());


    }

}