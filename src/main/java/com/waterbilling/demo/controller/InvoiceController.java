package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.dto.response.InvoiceLookupResponse;
import com.waterbilling.demo.service.InvoiceService;
import com.waterbilling.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private PdfService pdfExportService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> exportInvoicePdf(@PathVariable Integer id) throws Exception {
        // Giả sử bạn có service để lấy InvoiceLookupResponse theo id
        InvoiceDetailResponse response = invoiceService.getById(id);

        byte[] pdfBytes = pdfExportService.generateInvoicePdf(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @PostMapping("/create/{FacilityId}")
    public ResponseEntity<?> createInvoiceForFacility(@PathVariable("FacilityId") Integer facilityId) {
        invoiceService.createInvoiceForFacility(facilityId);
        return ResponseEntity.ok("Tạo hóa đơn thành công");
    }
}
