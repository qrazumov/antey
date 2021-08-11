package ru.qrazumov.antey.controllers;

import ru.qrazumov.antey.exception.ResourceNotFoundException;
import ru.qrazumov.antey.history.impl.InvoiceHistory;
import ru.qrazumov.antey.models.History;
import ru.qrazumov.antey.models.Invoice;
import ru.qrazumov.antey.models.User;
import ru.qrazumov.antey.repository.InvoiceRepository;
import ru.qrazumov.antey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceHistory invoiceHistory;

    @RequestMapping()
    public Collection<Invoice> invoices(Principal principal) {
        return userRepository
                .findByUsername
                        (principal.getName()).get().getInvoices();
    }

    @PatchMapping("/{invoiceId}/balance")
    public ResponseEntity<HashMap> updateInvoiceBalance(@PathVariable Long invoiceId,
                                                 @RequestBody Invoice invoiceDetails,
                                                        Principal principal) {

        Invoice invoice = invoiceRepository.findById(invoiceId).
                orElseThrow(() -> new ResourceNotFoundException("The invoice with id: " + invoiceId + " is not found"));

        Long newBalance = invoice.getBalance() + invoiceDetails.getBalance();
        invoice.setBalance(newBalance);
        History history = invoiceHistory.updateInvoiceBalance(invoice, principal);
        HashMap<String, Object> response = new HashMap<>();
        Invoice savedInvoice = invoiceRepository.save(invoice);
        response.put("invoice", savedInvoice);
        response.put("history", history);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<History> deleteInvoice(@PathVariable Long invoiceId, Principal principal) {

        Invoice invoice = invoiceRepository.findById(invoiceId).
                orElseThrow(() -> new ResourceNotFoundException("The invoice with id: " + invoiceId + " is not found"));

        invoiceRepository.delete(invoice);
        History history = invoiceHistory.deleteInvoice(invoice, principal);
        return new ResponseEntity<>(history, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<HashMap> addInvoice(@RequestBody Invoice invoiceDetails, Principal principal) {

        User user = userRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new ResourceNotFoundException("The invoice with name: " + principal.getName() + " is not found"));

        Invoice invoice = new Invoice(
                invoiceDetails.getName(),
                invoiceDetails.getCurrency(),
                invoiceDetails.getBalance(),
                user
        );

        Invoice savedInvoice = invoiceRepository.save(invoice);
        History history = invoiceHistory.addInvoice(invoice, principal);
        HashMap<String, Object> response = new HashMap<>();
        response.put("invoice", savedInvoice);
        response.put("history", history);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}