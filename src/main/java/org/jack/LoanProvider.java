package org.jack;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.csv.CSVFormat.*;

public class LoanProvider {

    private final List<Offer> offers;

    public LoanProvider() throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();

        final InputStream resourceAsStream = getClass().getResourceAsStream("/MarketDataforExercise.csv");

        CSVParser parser = CSVParser.parse(resourceAsStream, Charset.defaultCharset(), csvFormat);

        List<CSVRecord> records = parser.getRecords();

        offers = records.stream()
                .map( r -> new Offer( r.get(0), r.get(1), r.get(2)) )
                .sorted()
                .collect(Collectors.toList());
    }

    public void fill(Loan loan) {

        for(Offer o : offers) {

            Offer offer;

            if( loan.getFilled() + o.getAvailable() < loan.getAmount()) {
                //Total fill
                offer = new Offer( o );

            } else {
                //Partial fill
                offer = new Offer( o,  loan.getAmount() - loan.getFilled());
            }

            loan.fill( offer );
        }
    }
}
