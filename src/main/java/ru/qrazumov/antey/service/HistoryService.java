package ru.qrazumov.antey.service;

import ru.qrazumov.antey.models.History;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface HistoryService {
    History findById(Long historyId);
    History save(History history);
    void delete(History history);
    List<History> findTop5HistoryByUserIdOrderByCreatedAtDesc(Long id);
}
