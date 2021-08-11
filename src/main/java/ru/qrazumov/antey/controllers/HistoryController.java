package ru.qrazumov.antey.controllers;

import ru.qrazumov.antey.exception.ResourceNotFoundException;
import ru.qrazumov.antey.models.History;
import ru.qrazumov.antey.models.User;
import ru.qrazumov.antey.repository.UserRepository;
import ru.qrazumov.antey.service.impl.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/histories")
public class HistoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryServiceImpl historyServiceImpl;

    @RequestMapping()
    public List<History>  histories(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new ResourceNotFoundException("The user with name: " + principal.getName() + " is not found"));

        List<History> his = historyServiceImpl.findTop5HistoryByUserIdOrderByCreatedAtDesc( user.getId() );
        return his;
    }
}
