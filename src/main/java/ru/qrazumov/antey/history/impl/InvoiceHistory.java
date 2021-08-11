package ru.qrazumov.antey.history.impl;

import ru.qrazumov.antey.exception.ResourceNotFoundException;
import ru.qrazumov.antey.history.IHistory;
import ru.qrazumov.antey.models.History;
import ru.qrazumov.antey.models.Invoice;
import ru.qrazumov.antey.models.User;
import ru.qrazumov.antey.repository.UserRepository;
import ru.qrazumov.antey.service.impl.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;

@Component
public class InvoiceHistory implements IHistory {

    private static final String UPDATE_INVOICE_BALANCE_TMPL = "[%s] Счет: [id:%d] [название:%s] пополнен на сумму %d %s";
    private static final String DELETE_INVOICE_TMPL = "[%s] Счет: {id} :{name} закрыт";
    private static final String ADD_INVOICE_TMPL = "[%s] Счет: [id:%d] [название:%s] открыт";
    private static final String CURRENT_DATE_TIME = LocalDateTime.now().toString();

    @Autowired
    UserRepository userRepository;
    @Autowired
    HistoryServiceImpl historyServiceImpl;

    @Override
    public void save(Invoice invoice) {

    }

    @Override
    public History deleteInvoice(Invoice invoice, Principal principal) {
        Long id = invoice.getId();
        String name = invoice.getName();
        User user = userRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new ResourceNotFoundException("The user with name: " + principal.getName() + " is not found"));
        String record = String.format(DELETE_INVOICE_TMPL, CURRENT_DATE_TIME, id, name);

        History history = new History(record, user);
        return historyServiceImpl.save(history);

    }

    @Override
    public History addInvoice(Invoice invoice, Principal principal) {
        Long id = invoice.getId();
        String name = invoice.getName();
        User user = userRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new ResourceNotFoundException("The user with name: " + principal.getName() + " is not found"));
        String record = String.format(ADD_INVOICE_TMPL, CURRENT_DATE_TIME, id, name);

        History history = new History(record, user);
        return historyServiceImpl.save(history);
    }

    @Override
    public History updateInvoiceBalance(Invoice invoice, Principal principal) {
        Long id = invoice.getId();
        String name = invoice.getName();
        User user = userRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new ResourceNotFoundException("The user with name: " + principal.getName() + " is not found"));
        String record = String.format(UPDATE_INVOICE_BALANCE_TMPL,
                CURRENT_DATE_TIME,
                id,
                name,
                invoice.getBalance(),
                invoice.getCurrency());

        History history = new History(record, user);
        return historyServiceImpl.save(history);
    }

}