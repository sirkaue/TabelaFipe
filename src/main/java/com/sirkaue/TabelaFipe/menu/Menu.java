package com.sirkaue.TabelaFipe.menu;

import com.sirkaue.TabelaFipe.model.Dados;
import com.sirkaue.TabelaFipe.model.Modelos;
import com.sirkaue.TabelaFipe.service.ConsumoApi;
import com.sirkaue.TabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConverteDados conversor = new ConverteDados();

    Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        String menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consulta:
                """;

        System.out.println(menu);

        String opcao = sc.nextLine();
        String endereco;


        if (opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        String json = consumoApi.obterDados(endereco);
        System.out.println(json);

        List<Dados> marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta. ");
        String codigoMarca = sc.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);
        Modelos modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos()
                .stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }
}
