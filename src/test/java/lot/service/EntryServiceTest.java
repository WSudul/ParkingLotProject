package lot.service;

import lot.model.Lot;
import lot.model.LotEntry;
import lot.model.Plate;
import lot.repository.LotEntryRepository;
import lot.repository.LotRepository;
import lot.service.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EntryServiceTest {

    @MockBean
    private LotEntryRepository lotEntryRepositoryMock;
    @MockBean
    private LotRepository lotRepositoryMock;


    @Autowired
    private EntryService entryService;


    private Plate plate;
    private List<LotEntry> returned_entries;
    private LotEntry lotEntry_1;
    private LotEntry lotEntry_2;


    @Before
    public void setUp() {
        plate = new Plate();

        LotEntry lotEntry_1 = new LotEntry();
        LotEntry lotEntry_2 = new LotEntry();
        lotEntry_1.setId(1L);
        lotEntry_2.setId(2L);
        returned_entries = List.of(lotEntry_1, lotEntry_2);
    }

    @Test
    public void logEntry_ReturnTrue_WhenNewVehicleEnters() throws Exception {

        when(lotEntryRepositoryMock.findOneByPlateAndDateToIsNull(plate)).thenReturn(Optional.empty());
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
        when(lotEntryRepositoryMock.findAllByDateToIsNull()).thenReturn(returned_entries);
        List<LotEntry> entries = entryService.currentLotStatus();
        assertEquals(returned_entries, entries);
    }


    @Test
    public void entryHistory() throws Exception {
        String plateText = "123-ABC";

        when(lotEntryRepositoryMock.findAllByPlate_Plate(plateText)).thenReturn(returned_entries);
        List<LotEntry> entries = entryService.entryHistory(plateText);
        assertEquals(returned_entries, entries);
    }

    @Test
    public void lotDescription() throws Exception {
        Lot lotDescription = new Lot();
        lotDescription.setLocation("123-street");

        when(lotRepositoryMock.findFirstByLocationNotNull()).thenReturn(lotDescription);
        Lot returendDescriptin = entryService.lotDescription();
        assertEquals(lotDescription, returendDescriptin);
    }

}