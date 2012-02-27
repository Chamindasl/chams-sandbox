package com.chartis.dvt.core.model.exception;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
@SuppressWarnings("serial")
public class DvtException extends RuntimeException{

    public DvtException(String message) {
        super(message);
    }
    
    public DvtException() {
        super();
    }

    public DvtException(final Throwable t) {
        super(t);
    }
    
}
