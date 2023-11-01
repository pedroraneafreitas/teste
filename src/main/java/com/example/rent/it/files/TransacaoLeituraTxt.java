package com.example.rent.it.files;

import com.example.rent.it.armazenamento.FilaObj;
import com.example.rent.it.armazenamento.PilhaObj;
import com.example.rent.it.object.categoria.Categoria;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.CategoriaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransacaoLeituraTxt {



    public FilaObj<Item> leArquivoTxt(File file, List<Categoria> categorias, Usuario u) {

    BufferedReader entrada = null;
    String registro , tipoRegistro;
    String nomeItem, disponivel, descricao;
    Double valorDia;
    int qtdRegDadosGravados;
    int contaRegDadosLidos = 0;

    boolean isOk = true;


    FilaObj<Item> listaLida = new FilaObj<>(30);

    // try-catch para abrir o arquivo
        try {
        entrada = new BufferedReader(new FileReader(file));
    }
        catch (
    IOException erro) {
        System.out.println("Erro ao abrir o arquivo");
        System.exit(1);
    }


        System.out.println("\nLendo e processando o arquivo...");
        try {

        registro = entrada.readLine();

        while (registro != null) {

            tipoRegistro = registro.substring(0,2);
            if (tipoRegistro.equals("01")) {
                System.out.println("É um registro de trailer");
                qtdRegDadosGravados = Integer.parseInt(registro.substring(2,12));
                if (qtdRegDadosGravados != contaRegDadosLidos) {

                    isOk = false;
                }
            }
            else if (tipoRegistro.equals("02")) {
               int isDiponivel = 0;
                nomeItem= registro.substring(2,33).trim();
                valorDia= Double.valueOf(registro.substring(33,39).trim().replace(",", "."));
                disponivel = registro.substring(40,43).trim();
                descricao= registro.substring(43,191).trim();
                if(disponivel.equals("SIM")){
                   isDiponivel = 1;
                }
                contaRegDadosLidos++;


                Item a = new Item(nomeItem,descricao,valorDia, isDiponivel, categorias.get(0), u);
                listaLida.insert(a);
            }            else if (tipoRegistro.equals("03")) {
                int isDiponivel = 0;
                nomeItem= registro.substring(2,33).trim();
                valorDia= Double.valueOf(registro.substring(33,39).trim().replace(",", "."));
                disponivel = registro.substring(40,43).trim();
                descricao= registro.substring(43,191).trim();
                if(disponivel.equals("SIM")){
                    isDiponivel = 1;
                }
                contaRegDadosLidos++;


                Item a = new Item(nomeItem,descricao,valorDia, isDiponivel, categorias.get(1), u);
                listaLida.insert(a);
            }            else if (tipoRegistro.equals("04")) {
                int isDiponivel = 0;
                nomeItem= registro.substring(2,33).trim();
                valorDia= Double.valueOf(registro.substring(33,39).trim().replace(",", "."));
                disponivel = registro.substring(40,43).trim();
                descricao= registro.substring(43,191).trim();
                if(disponivel.equals("SIM")){
                    isDiponivel = 1;
                }
                contaRegDadosLidos++;


                Item a = new Item(nomeItem,descricao,valorDia, isDiponivel, categorias.get(2), u);
                listaLida.insert(a);
            }            else if (tipoRegistro.equals("05")) {
                int isDiponivel = 0;
                nomeItem= registro.substring(2,33).trim();
                valorDia= Double.valueOf(registro.substring(33,39).trim().replace(",", "."));
                disponivel = registro.substring(40,43).trim();
                descricao= registro.substring(43,191).trim();

                if(disponivel.equals("SIM")){
                    isDiponivel = 1;
                }
                contaRegDadosLidos++;


                Item a = new Item(nomeItem,descricao,valorDia, isDiponivel, categorias.get(3), u);
                listaLida.insert(a);
            }            else if (tipoRegistro.equals("06")) {
                int isDiponivel = 0;
                nomeItem= registro.substring(2,33).trim();
                valorDia= Double.valueOf(registro.substring(33,39).trim().replace(",", "."));
                disponivel = registro.substring(40,43).trim();
                descricao= registro.substring(43,191).trim();

                if(disponivel.equals("SIM")){
                    isDiponivel = 1;
                }
                contaRegDadosLidos++;


                Item a = new Item(nomeItem,descricao,valorDia, isDiponivel, categorias.get(4), u);
                listaLida.insert(a);
            }

            registro = entrada.readLine();
        }
        entrada.close();

    }
        catch (IOException erro) {
        isOk = false;
    }
        System.out.println(contaRegDadosLidos);
        if(isOk){
            return listaLida;
        }
        return null;



}
}
