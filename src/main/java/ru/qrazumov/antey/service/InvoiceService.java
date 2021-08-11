package ru.qrazumov.antey.service;

import ru.qrazumov.antey.models.Invoice;
import org.springframework.stereotype.Component;

@Component
public interface InvoiceService {
    Invoice findById(Long invoiceId);
    Invoice save(Invoice invoice);
    void delete(Invoice invoice);
}
