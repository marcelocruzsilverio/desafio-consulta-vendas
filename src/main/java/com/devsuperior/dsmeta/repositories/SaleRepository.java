package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT s FROM Sale s WHERE " +
            "(:minDate IS NULL OR s.date >= :minDate) AND " +
            "(:maxDate IS NULL OR s.date <= :maxDate) AND " +
            "(:sellerName IS NULL OR LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :sellerName, '%'))) ORDER BY s.id ASC")
    Page<Sale> findSales(LocalDate minDate, LocalDate maxDate, String sellerName, Pageable pageable);


}
