package lot.controller;

import lot.model.User;
import lot.model.UserRegistrationData;
import lot.service.UserService;
import lot.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    private UserService userService;

    @RequestMapping(value = "entrance", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody UserRegistrationData
                                                                userRegistrationData) {

        User user = userService.registerNewUserAccount(userRegistrationData);

        GenericResponse genericResponse = new GenericResponse();
        if (null == user) {
            //todo extract details ?
            genericResponse.getErrors().addError("User or plate already exists");

        }

        if (genericResponse.getErrors().hasErrors())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(genericResponse);
        else
            return ResponseEntity.created(null).body(genericResponse);

    }
}
