package ar.com.jss.controller;

import ar.com.jss.model.domain.User;
import ar.com.jss.model.domain.UserResource;
import ar.com.jss.service.data_access.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
@RestController
@RequestMapping(Path.USERS)
public class UserController {

    private final UserDataAccess userDataAccess;

    @Autowired
    public UserController(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UserResource>> users() {
        return ResponseEntity.ok(userDataAccess.getUsers());
    }


    @RequestMapping(value = "/{user}",method = RequestMethod.GET)
    public ResponseEntity<UserResource> user(@PathVariable long user) {
        return ResponseEntity.ok(userDataAccess.getUser(user));
    }
}
