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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            
          // PrintStream saida;

            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
                       
            System.out.println("O cliente conectou ao servidor");

            //Prepara para leitura do teclado
            System.out.println("Lado Cliente");
                        
            Random random = new Random();
    
           int vet[] = new int[2];

            oos = new ObjectOutputStream(this.cliente.getOutputStream());
            
            for(int i=0; i<vet.length; i++){
                vet[i] = random.nextInt(10)+1;
            }
            oos.writeObject(vet);       
       
           //Envia mensagem ao servidor
            
            ois = new ObjectInputStream(this.cliente.getInputStream());
            String msgReceive = (String) ois.readObject();
            System.out.println("[Cliente] Recebido: " + msgReceive);
      
            this.cliente.close();
            System.out.println("Fim do cliente!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
