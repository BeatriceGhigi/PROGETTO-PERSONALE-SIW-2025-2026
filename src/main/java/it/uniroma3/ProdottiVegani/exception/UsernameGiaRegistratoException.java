package it.uniroma3.ProdottiVegani.exception;

public class UsernameGiaRegistratoException extends RuntimeException {

    public UsernameGiaRegistratoException(String username) {
        super("Lo username '" + username + "' è già in uso");
    }
}