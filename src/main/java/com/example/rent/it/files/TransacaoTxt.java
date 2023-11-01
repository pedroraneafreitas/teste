package com.example.rent.it.files;

import com.example.rent.it.armazenamento.PilhaObj;
import com.example.rent.it.dto.TransacaoDto.TransacaoRetornoDto;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.ordenacao.ListaObj;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class TransacaoTxt {

    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario
    public File getListaDeAlugueis(PilhaObj<TransacaoRetornoDto> transacoes) {
        File file = null;
        File fileBack = null;
        String nomeArq = "";
        FileWriter arq = null;
        Formatter saida = null;
        String header = "";
        String corpo = "";
        String trealer = "";
        int cont = 0;

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }



        try {

            nomeArq = transacoes.peek().getNomeUsu();
            nomeArq +=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            boolean ok = true;
            nomeArq += ".txt";

            try {
                file = new File(this.diretorioBase + "/" + nomeArq);

                arq = new FileWriter(file);
                saida = new Formatter(arq);


            } catch (IOException e) {
                System.out.println("erro ao abrir o arquivo");
                e.printStackTrace();
                System.exit(1);

            }

            try {
                header += "00Alugaveis";
                header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                header +="01";


                // Grava o registro de header
               //  gravaRegistro(header, nomeArq);
                   for (int i = 0; i < transacoes.getTopo();i++) {
                    TransacaoRetornoDto transacao = transacoes.pop();

                    corpo = "02";
                    corpo += String.format("%30.5s", transacao.getNomeUsu());
                    corpo += String.format("%30.8s", transacao.getEmailUsu());
                    corpo += String.format("%-25.50s", transacao.getNomeItem());
                    corpo += String.format("%-15.40s", transacao.getCategoria());
                    corpo += String.format("%02.s", transacao.getDtFim());
                    corpo += String.format("%05.2f", transacao.getValorItem());
                    corpo += String.format("%05.2f", transacao.getValorFinal());
                    corpo += String.format("%20.s", transacao.getDisponivel());
                    corpo += String.format("%40.s", transacao.getDescricao());
                    gravaRegistro(corpo, nomeArq);
                    cont++;

                }

                trealer = "01";
                trealer += String.format("%05d", cont);
                gravaRegistro(trealer, nomeArq);

            } catch (FormatterClosedException e) {
                System.out.println("Erro ao gravar o arquivo");
                ok = false;

            } finally {
                saida.close();
                try {

                    arq.close();


                } catch (IOException e) {
                    System.out.println("erro ao fechar o arquivo");
                    ok = false;
                }
            }
            if (!ok) {
                System.exit(1);
            }

            fileBack = file;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileBack;
    }

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }

    public File criaArquivo(String nomeArqui, byte[] arqByte){
        File retorno = null;

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }
        nomeArqui += ".txt";

        try (
                FileOutputStream fileOutputStream =
                     new FileOutputStream(this.diretorioBase + "/" + nomeArqui)){
            fileOutputStream.write(arqByte);

            retorno = new File(this.diretorioBase + "/" + nomeArqui);

        }catch (IOException e){
            e.printStackTrace();
        }

        return retorno;

    }
}
