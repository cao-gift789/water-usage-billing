package com.waterbilling.demo.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_watermeterreading")  // Đảm bảo tên bảng là chính xác
public class InvoiceWaterMeterReading {

    @EmbeddedId
    private InvoiceWaterMeterReadingId id;
	
	@ManyToOne
   @MapsId("invoiceId")
    @JoinColumn(name = "InvoiceID", foreignKey = @ForeignKey(name = "fk_invoice_watermeterreading_invoice"))
    private Invoice invoiceWaterMeterReading_invoice;
	
	
	
	
    @ManyToOne
    @MapsId("readingId")
    @JoinColumn(name = "ReadingID", foreignKey = @ForeignKey(name = "fk_invoice_watermeterreading_reading"))
    private WaterMeterReading  invoiceWaterMeterReading_waterMeterReading;

    // Constructors
    public InvoiceWaterMeterReading() {
    }

    public InvoiceWaterMeterReading(Invoice invoice, WaterMeterReading waterMeterReading) {
        this.invoiceWaterMeterReading_invoice = invoice;
        this.invoiceWaterMeterReading_waterMeterReading = waterMeterReading;
//        this.id = new InvoiceWaterMeterReadingId(invoice.getInvoiceId(), waterMeterReading.getReadingId());
    }

	public Invoice getInvoiceWaterMeterReading_invoice() {
		return invoiceWaterMeterReading_invoice;
	}

	public void setInvoiceWaterMeterReading_invoice(Invoice invoiceWaterMeterReading_invoice) {
		this.invoiceWaterMeterReading_invoice = invoiceWaterMeterReading_invoice;
	}

	public WaterMeterReading getInvoiceWaterMeterReading_waterMeterReading() {
		return invoiceWaterMeterReading_waterMeterReading;
	}

	public void setInvoiceWaterMeterReading_waterMeterReading(
			WaterMeterReading invoiceWaterMeterReading_waterMeterReading) {
		this.invoiceWaterMeterReading_waterMeterReading = invoiceWaterMeterReading_waterMeterReading;
	}

//    public InvoiceWaterMeterReadingId getId() {
//        return id;
//    }
//
//    public void setId(InvoiceWaterMeterReadingId id) {
//        this.id = id;
//    }

    
}
