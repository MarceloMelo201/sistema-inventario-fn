package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.repositories.PecaRepository;
import com.bytenest.sistema_inventario_fn.services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    PecaRepository pecaRepository;

    @Autowired
    PecaService pecaService;
    
}
