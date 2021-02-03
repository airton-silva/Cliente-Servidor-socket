/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class Cliente implements Runnable{
    public  Socket cliente;
    
    public Cliente( Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            
            
            ObjectOutputStream saida;
            ObjectInputStream entrada;
            System.out.println("O cliente conectou ao servidor");

            //Prepara para leitura do teclado
            System.out.println("Lado Cliente");
            Scanner teclado = new Scanner(System.in);
            
            Random random = new Random();
            int numeros = random.nextInt()* 100;

            //Cria  objeto para enviar a mensagem ao servidor
            saida = new ObjectOutputStream(this.cliente.getOutputStream());
            entrada = new ObjectInputStream(this.cliente.getInputStream());
        
            saida.write(numeros);
            
            int soma = entrada.readInt();
            
            System.out.println("soma = "+soma);

            //Envia mensagem ao servidor
//            while(teclado.hasNextLine()){
//                saida.println(teclado.nextLine());          
//            }


//            while(saida){
//                saida.println(numeros);          
//            }

            saida.close();
       
            teclado.close();
            this.cliente.close();
            System.out.println("Fim do cliente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
