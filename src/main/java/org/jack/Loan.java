package org.jack;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Loan {

    private Long amount;

    private List<Offer> offers = new ArrayList<>();

    @Getter
    private Long filled = 0l;

    public Loan(Long amount) {
        this.amount = amount;
    }

    public void print() {

        log.info("Requested amount: {}", getAmountAsCurrency());
        log.info("Rate: {}", getRate());
        log.info("Monthly repayment: ", getMonthlyRepayment());
        log.info("Total repayment: {}", getTotalRepayment());
    }

    private String getAmountAsCurrency() {
       return "£" + getAmount();
    }

    private double getTotalRepayment() {
        double r = getRate() / 100;
        log.info("r {}", r);
//        a * Math.pow(1 + r/12, month)
        double totalRepayment = Math.pow(1.0 + getRate(), 36) * amount;
        return totalRepayment;
    }

    private double getMonthlyRepayment() {

        return getTotalRepayment() / 36;
    }

    private Double getRate() {

        Double rate = 0.0;

        for(Offer o : offers) {
            Double percentage = o.getAvailable().doubleValue() / amount;

//            log.info("per {}", percentage);

            double r = o.getRate() * percentage;

//            log.info("r {}", r);

            rate = rate + (o.getRate() * percentage);
        }

       return rate / offers.size() * 100;
    }

    public void fill(Offer offer) {
        this.filled = filled + offer.getAvailable();
        offers.add( offer );
    }
}
