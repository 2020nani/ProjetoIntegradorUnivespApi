package br.com.rainmonitoring.notificacaorisco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRiscoRepository extends JpaRepository<NotificacaoRisco, Long> {
}
