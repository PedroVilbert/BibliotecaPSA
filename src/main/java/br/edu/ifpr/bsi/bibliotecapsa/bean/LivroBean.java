package br.edu.ifpr.bsi.bibliotecapsa.bean;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

// O Namede serve para identificar este Bean através da página web criada
@Named("LivroBean")
@ViewScoped // O ViewScoped serve para manter o estado do Bean durante a visualização da página
public class LivroBean implements Serializable {
}
