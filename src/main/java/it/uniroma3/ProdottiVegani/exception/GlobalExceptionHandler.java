package it.uniroma3.ProdottiVegani.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gestisciEntitaNonTrovata(IllegalArgumentException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String gestisciOperazioneNonPermessa(IllegalStateException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/operazione-non-permessa";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String gestisciAccessoNegato(AccessDeniedException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/403";
    }
}