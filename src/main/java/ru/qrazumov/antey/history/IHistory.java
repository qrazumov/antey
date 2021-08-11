package ru.qrazumov.antey.history;

import ru.qrazumov.antey.models.History;
import ru.qrazumov.antey.models.Invoice;

import java.security.Principal;

public interface IHistory {
    History addInvoice(Invoice invoice, Principal principal);
    void save(Invoice invoice);
    History deleteInvoice(Invoice invoice, Principal principal);
    History updateInvoiceBalance(Invoice invoice, Principal principal);
}
