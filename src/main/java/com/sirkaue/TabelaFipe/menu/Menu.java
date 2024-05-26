package com.sirkaue.TabelaFipe.menu;

import com.sirkaue.TabelaFipe.service.ConsumoApi;

import java.util.Scanner;

public class Menu {
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://parellelum.com.br/fipe/api/v1";

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
    }
}
