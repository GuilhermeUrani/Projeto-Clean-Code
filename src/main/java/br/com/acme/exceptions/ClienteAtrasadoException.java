package br.com.acme.exceptions;

public class ClienteAtrasadoException extends RuntimeException {

    private static final long serialVersionUID = -8332142176853224884L;

    public ClienteAtrasadoException(String message) {
        super(message);
    }
}
