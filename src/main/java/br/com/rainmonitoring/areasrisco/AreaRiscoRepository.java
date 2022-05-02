package br.com.rainmonitoring.areasrisco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRiscoRepository extends JpaRepository<AreaRisco ,Long> {
}
