package ru.qrazumov.antey.controllers;

import ru.qrazumov.antey.models.ECurrency;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @RequestMapping()
    public ECurrency[] currency(Principal principal) {
        return ECurrency.values();
    }

}
