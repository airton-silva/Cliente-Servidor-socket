/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class MainCliente {
        public static void main(String[] args) throws IOException {

        // Neste caso, usa-se o IP da máquina local (127.0.0.1)
        // e a porta da aplicação ServidorDeEco (10002).
        
        int i = 0;
        
        while(i < 3){
            Socket socket = new Socket("127.0.0.1", 10002);

            /*Cria um novo objeto Cliente com a conexão socket para que seja executado em um novo processo.
            Permitindo assim a conexão de vário clientes com o servidor.*/

            Cliente c = new Cliente(socket);
            Thread ti = new Thread(c);
            ti.start();

                i++;

        }
           
    }
}
