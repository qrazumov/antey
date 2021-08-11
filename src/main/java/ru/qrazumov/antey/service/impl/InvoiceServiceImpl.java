package ru.qrazumov.antey.service.impl;

import ru.qrazumov.antey.exception.ResourceNotFoundException;
import ru.qrazumov.antey.models.Invoice;
import ru.qrazumov.antey.repository.InvoiceRepository;
import ru.qrazumov.antey.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice findById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).
                orElseThrow(() -> new ResourceNotFoundException("The invoice with id: " + invoiceId + " is not found"));
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

}