package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {
}
