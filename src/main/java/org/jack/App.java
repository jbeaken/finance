package org.jack;


import java.io.IOException;


public class App {
    public static void main( String[] args ) throws IOException {

        LoanProvider loanProvider = new LoanProvider();

        Long loanAmount = Long.parseLong( args[0] );

        Loan loan = new Loan( loanAmount );

        loanProvider.fill( loan );

        loan.print();
    }

}
