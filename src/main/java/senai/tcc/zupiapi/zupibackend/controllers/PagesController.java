package senai.tcc.zupiapi.zupibackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-pais";
    }

    @GetMapping("/selecao-perfil")
    public String selecaoPerfil() {
        return "selecao-perfil";
    }
}
