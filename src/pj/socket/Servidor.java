/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
                
                int msgReceive[] = new int[1000000];
                    msgReceive = (int[]) ois.readObject();
                    int rsp = soma(msgReceive);
                   
                    System.out.println("Soma dos Numero: " + rsp); 
                    
                    long tf = (System.currentTimeMillis() - init);
                    
                // Cria uma saída para escrever no socket
                ObjectOutputStream oos = new ObjectOutputStream(this.cliente.getOutputStream());
                oos.writeInt(rsp);
                oos.writeLong(tf);
                oos.flush();  
                               
                System.out.println("Time do processo: " +tf );

                ois.close();
                oos.close();
                this.cliente.close();     

        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
