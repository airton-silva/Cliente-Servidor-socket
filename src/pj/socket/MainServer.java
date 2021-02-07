/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class MainServer {
    
    
    
    public static void main(String[] args) throws IOException {
        
                //Cria um socket na porta 10002
           ServerSocket servidor = new ServerSocket(10002);
        
        System.out.println("Porta 10002 aberta!");

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        
        System.out.println("Aguardando conexão do cliente...");   
        
        while (true) {                              
                      
            Socket cliente = servidor.accept();            
                     
            // Cria uma thread do servidor para tratar a conexão
            Servidor tratamento = new Servidor(cliente);
            Thread s1 = new Thread(tratamento);
            //Thread s2 = new Thread(tratamento);  
            
            // Inicia a thread para o cliente conectado
            s1.start();
            //s2.start();


        }        
        
        
    }
    
    
    
}
