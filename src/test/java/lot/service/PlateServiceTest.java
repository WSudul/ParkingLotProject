package lot.service;

import lot.model.Plate;
import lot.repository.PlateRepository;
import lot.service.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import plate.PlateValidation;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PlateServiceTest {

    @MockBean
    private PlateRepository plateRepositoryMock;

    @Autowired
    private PlateService plateService;

    @Test
    public void findMatchingPlate_shouldReturnExistingPlate() throws Exception {
        String plateText = "ABC-12345";
        PlateValidation.Plate plate = PlateValidation.Plate.newBuilder().setPlate(plateText).build();
        Optional<Plate> existingPlate = Optional.of(new Plate());

        when(plateRepositoryMock.findOneByPlate(plateText)).thenReturn(existingPlate);
        assertEquals(existingPlate, plateService.findMatchingPlate(plate));

        verify(plateRepositoryMock, times(1)).findOneByPlate(plateText);
    }

    @Test
    public void findMatchingPlate_shouldReturnNull_WhenNoMatchingPlateExists() throws Exception {
        String plateText = "ABC-MISING";
        PlateValidation.Plate plate = PlateValidation.Plate.newBuilder().setPlate(plateText).build();

        when(plateRepositoryMock.findOneByPlate(plateText)).thenReturn(Optional.ofNullable(null));
        assertEquals(null, plateService.findMatchingPlate(plate));

        verify(plateRepositoryMock, times(1)).findOneByPlate(plateText);
    }

}