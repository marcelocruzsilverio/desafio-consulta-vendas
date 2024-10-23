package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "name", required = false) String sellerName,
			@PageableDefault(size = 10) Pageable pageable) {

		Page<SaleReportDTO> report = service.getSalesReport(minDate, maxDate, sellerName, pageable);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	    public ResponseEntity<List<SaleSummaryDTO>> getSummary(
	            @RequestParam(value = "minDate", required = false) String minDate,
	            @RequestParam(value = "maxDate", required = false) String maxDate) {
	        List<SaleSummaryDTO> list = service.getSalesSummary(minDate, maxDate);
	        return ResponseEntity.ok(list);
	    }
}
