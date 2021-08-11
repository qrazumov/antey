package ru.qrazumov.antey.service.impl;

import ru.qrazumov.antey.exception.ResourceNotFoundException;
import ru.qrazumov.antey.models.History;
import ru.qrazumov.antey.repository.HistoryRepository;
import ru.qrazumov.antey.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History findById(Long HistoryId) {
        return historyRepository.findById(HistoryId).
                orElseThrow(() -> new ResourceNotFoundException("The History with id: " + HistoryId + " is not found"));
    }

    @Override
    public History save(History History) {
        return historyRepository.save(History);
    }

    @Override
    public void delete(History History) {
        historyRepository.delete(History);
    }

    @Override
    public List<History> findTop5HistoryByUserIdOrderByCreatedAtDesc(Long id) {
        return historyRepository.findTop5HistoryByUserIdOrderByCreatedAtDesc(id);
    }
}
