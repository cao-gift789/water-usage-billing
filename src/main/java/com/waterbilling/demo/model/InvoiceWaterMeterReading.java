package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_watermeterreading")  // Đảm bảo tên bảng là chính xác
public class InvoiceWaterMeterReading {

    @EmbeddedId
    private InvoiceWaterMeterReadingId id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id", foreignKey = @ForeignKey(name = "fk_invoice_watermeterreading_invoice"))
    private Invoice invoice;

    @ManyToOne
    @MapsId("readingId")
    @JoinColumn(name = "reading_id", foreignKey = @ForeignKey(name = "fk_invoice_watermeterreading_reading"))
    private WaterMeterReading waterMeterReading;

    // Constructors
    public InvoiceWaterMeterReading() {
    }

    public InvoiceWaterMeterReading(Invoice invoice, WaterMeterReading waterMeterReading) {
        this.invoice = invoice;
        this.waterMeterReading = waterMeterReading;
        this.id = new InvoiceWaterMeterReadingId(invoice.getInvoiceId(), waterMeterReading.getReadingId());
    }

    public InvoiceWaterMeterReadingId getId() {
        return id;
    }

    public void setId(InvoiceWaterMeterReadingId id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public WaterMeterReading getWaterMeterReading() {
        return waterMeterReading;
    }

    public void setWaterMeterReading(WaterMeterReading waterMeterReading) {
        this.waterMeterReading = waterMeterReading;
    }
}
