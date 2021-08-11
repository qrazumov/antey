package ru.qrazumov.antey.repository;

import ru.qrazumov.antey.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findTop5HistoryByUserIdOrderByCreatedAtDesc(Long id);
}
