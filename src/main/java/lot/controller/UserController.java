package lot.controller;

import lot.model.UpdateUserData;
import lot.model.User;
import lot.model.UserRegistrationData;
import lot.service.UserService;
import lot.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody UserRegistrationData
                                                                userRegistrationData, BindingResult bindingResult) {

        GenericResponse genericResponse = new GenericResponse();
        if (bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(
                    Collectors.toList());
            genericResponse.getErrors().setErrors(errors);

        } else {
            User user = userService.registerNewUserAccount(userRegistrationData);
            if (null == user) {
                //todo extract details ?
                genericResponse.getErrors().addError("User or plate already exists");

            }
        }

        if (genericResponse.getErrors().hasErrors())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(genericResponse);
        else
            return ResponseEntity.created(null).body(genericResponse);

    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> addPlate(@Valid @RequestBody UpdateUserData updateUserData,
                                                    @AuthenticationPrincipal Principal principal) {
        GenericResponse genericResponse = new GenericResponse();


        String name = principal.getName();

        Optional<User> user = userService.findUser(name);

        if (user.isPresent()) {
            if (false == userService.updateUser(user.get(), updateUserData)) ;
        } else {
            genericResponse.getErrors().addError("Update failed");
        }

        if (genericResponse.getErrors().hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
        else
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);

    }
}
