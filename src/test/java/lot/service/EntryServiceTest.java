package lot.service;

import lot.model.LotEntry;
import lot.model.Plate;
import lot.repository.LotEntryRepository;
import lot.service.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EntryServiceTest {

    @MockBean
    private LotEntryRepository lotEntryRepositoryMock;

    @Autowired
    private EntryService entryService;


    private Plate plate;

    @Before
    public void setUp() {
        plate = new Plate();


    }

    @Test
    public void logEntry_ReturnTrue_WhenNewVehicleEnters() throws Exception {

        when(lotEntryRepositoryMock.findOneByPlateAndDateToIsNull(plate)).thenReturn(Optional.ofNullable(null));
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
    public void currentLotStatus_ReturnAllLotEntries() throws Exception {
        LotEntry lotEntry1 = new LotEntry();
        lotEntry1.setId(1L);
        LotEntry lotEntry2 = new LotEntry();
        lotEntry2.setId(2L);
        Set<LotEntry> lotEntries = Set.of(lotEntry1, lotEntry2);

        when(lotEntryRepositoryMock.findAllByDateToIsNull()).thenReturn(lotEntries);
        Set<LotEntry> entries = entryService.currentLotStatus();
        assertEquals(lotEntries, entries);

    }

    @Test
    public void getPlatesInLot_ReturnAllPlatesInLot() throws Exception {
        LotEntry lotEntry1 = new LotEntry();
        lotEntry1.setId(1L);
        Plate plate1 = new Plate();
        plate1.setPlate("123");
        lotEntry1.setPlate(plate1);
        LotEntry lotEntry2 = new LotEntry();
        lotEntry2.setId(2L);
        Plate plate2 = new Plate();
        plate2.setPlate("456");
        lotEntry2.setPlate(plate2);
        Set<LotEntry> lotEntries = Set.of(lotEntry1, lotEntry2);
        when(lotEntryRepositoryMock.findAllByDateToIsNull()).thenReturn(lotEntries);

        Set<Plate> entries = entryService.GetPlatesInLot();
        assertEquals(Set.of(plate1, plate2), entries);
    }


}