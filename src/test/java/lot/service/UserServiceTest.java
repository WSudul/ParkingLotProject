package lot.service;

import lot.service.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//todo implement tests

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserServiceTest {

    @Test
    public void registerNewUserAccount_AddsNewUser_WhenGivenValidData() throws Exception {
    }

    @Test
    public void registerNewUserAccount_Fails_WhenEmailExists() throws Exception {
    }


    @Test
    public void registerNewUserAccount_Fails_WhenPlateExists() throws Exception {
    }


    @Test
    public void registerNewUserAccount_AddsNewUser_NoNickname() throws Exception {
    }

    @Test
    public void registerNewUserAccount_AddsNewUser_NoPlate() throws Exception {
    }
}