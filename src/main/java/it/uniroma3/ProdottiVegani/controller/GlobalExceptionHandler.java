package it.uniroma3.ProdottiVegani.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String gestisciEntitaNonTrovata(IllegalArgumentException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String gestisciOperazioneNonPermessa(IllegalStateException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/operazione-non-permessa";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String gestisciAccessoNegato(AccessDeniedException ex, Model model) {
        model.addAttribute("messaggio", ex.getMessage());
        return "error/403";
    }
}