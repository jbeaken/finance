package org.jack;

import lombok.Data;

@Data
public class Offer implements  Comparable{

    private String lender;
    private Double rate;
    private Long available;


    public Offer(String lender, String rate, String available) {

        this.lender = lender;

        this.rate = Double.parseDouble(rate);

        this.available = Long.parseLong( available );
    }

    public Offer(Offer o, long available) {
        this(o);
        this.available = available;
    }

    public Offer(Offer o) {
        this.lender = o.lender;
        this.rate = o.rate;
        this.available = o.available;
    }

    @Override
    public int compareTo(Object o) {
        Offer that = (Offer) o;
        return this.getRate().compareTo(((Offer) o).getRate());
    }
}
