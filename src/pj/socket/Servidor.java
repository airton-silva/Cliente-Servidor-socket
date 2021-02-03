/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.DataOutputStream;
import java.io.IOException;
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

   public int soma(int n){
       int resp =0;
       int aux = n;
       while(aux !=10)
           n = +n;
       return n;
   }
    @Override
    public void run() {
        
        long init = System.currentTimeMillis();
        
         System.out.println("Nova conexao com o cliente " + 
                 this.cliente.getInetAddress().getHostAddress());
         System.out.println("Lado Servidor");
         
        try {
           // DataOutputStream sai = new DataOutputStream(cliente.getOutputStream());
            
            Scanner s = null;
            
            ObjectInputStream entrada = new ObjectInputStream(this.cliente.getInputStream());
            
            ObjectOutputStream saida = new ObjectOutputStream(this.cliente.getOutputStream());
            
            int entRecebida = (int)entrada.readInt();
           // s = new Scanner(this.cliente.getInputStream());
            
           int resp=0;
                 resp = soma(entRecebida);

            //Finaliza objetos
            saida.write(resp);
            System.out.println("Tempo de conexão ="+ (System.currentTimeMillis() - init));
            saida.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
