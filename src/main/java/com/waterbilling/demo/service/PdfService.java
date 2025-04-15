package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.dto.response.InvoiceLookupResponse;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class PdfService {
    public byte[] generateInvoicePdf(InvoiceDetailResponse response) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Tiêu đề hóa đơn
        document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
                .setFontSize(20)
                .setBold()
                .setMarginBottom(20));

        // Thông tin hóa đơn
        document.add(new Paragraph("Mã hóa đơn: " + response.getInvoiceId()));
        document.add(new Paragraph("Tên khách hàng: " + response.getCustomerName()));
        document.add(new Paragraph("Địa chỉ: " + response.getAddress()));
        document.add(new Paragraph("Ngày tạo: " + formatDate(response.getCreationDate())));
        document.add(new Paragraph("Ngày thanh toán: " + formatDate(response.getPaymentDate())));
        document.add(new Paragraph("Trạng thái: " + response.getStatus()));

        // Bảng chỉ số đồng hồ nước
        document.add(new Paragraph("Chi tiết chỉ số đồng hồ nước").setMarginTop(20));
        Table table = new Table(UnitValue.createPercentArray(new float[]{50, 50}));
        table.addHeaderCell("Thời gian").addHeaderCell("Chỉ số (m³)");
        for (Map.Entry<String, BigDecimal> entry : response.getWaterMeterReadingResponses().entrySet()) {
            table.addCell(entry.getKey()).addCell(entry.getValue().toString());
        }
        document.add(table);

        // Tổng hợp
        document.add(new Paragraph("Tổng tiêu thụ: " + response.getTotalConsumption() + " m³").setMarginTop(20));
        document.add(new Paragraph("Tổng tiền: " + response.getTotalPrice() + " VNĐ"));
        document.add(new Paragraph("Phí phạt: " + response.getPenaltyFee() + " VNĐ"));

        document.close();
        return baos.toByteArray();
    }

    private String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "N/A";
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
