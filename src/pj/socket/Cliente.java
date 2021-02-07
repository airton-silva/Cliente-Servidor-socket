/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


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

            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
                       
            System.out.println("O cliente conectou ao servidor");

            //Prepara para leitura do teclado
            System.out.println("Lado Cliente");
                        
            Random random = new Random();    
            int vet[] = new int[1000000];

            oos = new ObjectOutputStream(this.cliente.getOutputStream());
            
            for(int i=0; i<vet.length; i++){
                vet[i] = random.nextInt(100)+1;
            }
            
            oos.writeObject(vet);       
       
           //Envia mensagem ao servidor
            
            ois = new ObjectInputStream(this.cliente.getInputStream());
            int msgReceive = (Integer)ois.readInt();
            long tmp = (Long)ois.readLong();
           
                       
            System.out.println("[Cliente] Recebido soma dos numeros: " + msgReceive 
                    + "\n Tempo de Excução do processo: " + tmp);
          //  System.out.println("[Cliente] Tempo de Excução do processo: " + tmp);
 
            ois.close();
            oos.close();
      
            this.cliente.close();
            
            System.out.println("Fim do cliente!");
            

            
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        
    }
}
