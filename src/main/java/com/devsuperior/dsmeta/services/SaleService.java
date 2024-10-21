package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SaleReportDTO> getSalesReport(String minDateStr, String maxDateStr, String sellerName, Pageable pageable) {
	    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	    LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty()) ? today : LocalDate.parse(maxDateStr);
	    LocalDate minDate = (minDateStr == null || minDateStr.isEmpty()) ? maxDate.minusYears(1) : LocalDate.parse(minDateStr);

	    Page<Sale> result = repository.findSales(minDate, maxDate, sellerName, pageable);
	    return result.stream()
	            .map(sale -> new SaleReportDTO(sale.getId(), sale.getDate().toString(), sale.getAmount(), sale.getSeller().getName()))
	            .collect(Collectors.toList());
	}
	
	public List<SaleSummaryDTO> getSalesSummary(String minDateStr, String maxDateStr) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty()) ? today : LocalDate.parse(maxDateStr);
        LocalDate minDate = (minDateStr == null || minDateStr.isEmpty()) ? maxDate.minusYears(1) : LocalDate.parse(minDateStr);

        List<Object[]> result = repository.findSalesSummary(minDate, maxDate);
        return result.stream()
            .map(obj -> new SaleSummaryDTO((String) obj[0], (Double) obj[1]))
            .collect(Collectors.toList());
    }

}
