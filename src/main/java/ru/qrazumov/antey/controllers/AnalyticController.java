package ru.qrazumov.antey.controllers;

import ru.qrazumov.antey.history.impl.InvoiceHistory;
import ru.qrazumov.antey.models.Invoice;
import ru.qrazumov.antey.repository.InvoiceRepository;
import ru.qrazumov.antey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/analytics")
public class AnalyticController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceHistory invoiceHistory;

    @RequestMapping("/summary-information-on-invoices")
    public Map< String, List<Object> > summaryInformationOnInvoices(Principal principal) {

        Collection<Invoice> invoices = userRepository
                .findByUsername
                        (principal.getName()).get().getInvoices();

        Map<String, List<Object> > response = new HashMap<>();
        List<Object> names = invoices.stream().map(name -> name.getName() + " [" + name.getCurrency() + "]").collect(Collectors.toList());
        List<Object> balances = invoices.stream().map(Invoice::getBalance).collect(Collectors.toList());
        response.put("labels", names);
        response.put("datasets", balances);

        return response;
    }
    @RequestMapping("summary-information-on-currencies")
    public Map<String, Object> summaryInformationOnCurrencies(Principal principal){

        Collection<Invoice> invoices = userRepository
                .findByUsername
                        (principal.getName()).get().getInvoices();



        Map<Object, Object> response = invoices.stream().
                collect(Collectors.groupingBy(Invoice::getCurrency)).entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                entry -> entry.getValue().stream().mapToLong(Invoice::getBalance).sum()
        ));
        Map<String, Object> finalResponse = new HashMap<>();
        List<Object> labels = response.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        List<Object> datasets = response.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        finalResponse.put("labels", labels);
        finalResponse.put("datasets", datasets);

return finalResponse;










    }

}