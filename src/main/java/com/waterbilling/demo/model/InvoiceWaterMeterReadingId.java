package com.waterbilling.demo.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvoiceWaterMeterReadingId implements Serializable {

    private Integer invoiceId;
    private Integer readingId;

    public InvoiceWaterMeterReadingId() {
    }

    public InvoiceWaterMeterReadingId(Integer invoiceId, Integer readingId) {
        this.invoiceId = invoiceId;
        this.readingId = readingId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getReadingId() {
        return readingId;
    }

    public void setReadingId(Integer readingId) {
        this.readingId = readingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceWaterMeterReadingId that = (InvoiceWaterMeterReadingId) o;
        return Objects.equals(invoiceId, that.invoiceId) &&
               Objects.equals(readingId, that.readingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, readingId);
    }
}
