package com.loan.mortgageMicroService.exceptions;

public class MortgageApiRequestException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public MortgageApiRequestException(String message)
    {
        super(message);
    }

    public MortgageApiRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MortgageApiRequestException()
    {
        super();
    }

    public MortgageApiRequestException(Throwable cause)
    {
        super(cause);
    }


}
