/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;



/**
 *
 * @author Acer
 */
public class Servidor implements Runnable{
    
       private Socket cliente;

   public Servidor(Socket cliente){
       this.cliente = cliente;
   }

   public Integer soma(int n[]){
       int i=0, soma =0;
       for(i=i; i<n.length;i++){
           soma= soma+n[i];
       }
       return soma;
   }
    @Override
    public void run() {
        
        
        
         System.out.println("Nova conexao com o cliente " + 
                 this.cliente.getInetAddress().getHostAddress());
         System.out.println("Lado Servidor");
         
        try {       
            
      
                long  init = 0;
                init = System.currentTimeMillis();
                      
                ObjectInputStream ois = new ObjectInputStream(this.cliente.getInputStream());
                
                int msgReceive[] = new int[10];
                    msgReceive = (int[]) ois.readObject();
                    int rsp = soma(msgReceive);
//                    
//                    for(int i =0; i< msgReceive.length;i++){
//                        System.out.println("[Servidor] Recebido: " + msgReceive[i]);
//                    }
//                   
                    System.out.println("Soma: " + rsp); 
 
                // Cria uma saída para escrever no socket
                ObjectOutputStream oos = new ObjectOutputStream(this.cliente.getOutputStream());
                oos.writeInt(rsp);
                //oos.writeObject(rsp);
                oos.flush();
               
                long tf = (System.currentTimeMillis() - init);
                
                System.out.println("Time total: " +tf );
                
 
                ois.close();
                oos.close();
                this.cliente.close();
            
       
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
    //System.out.println("Tempo de conexão ="+ (System.currentTimeMillis() - init));
