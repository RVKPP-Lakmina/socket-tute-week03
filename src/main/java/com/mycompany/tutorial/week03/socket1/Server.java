/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket1;

/* **Import the BufferedReader class, which reads text from a character-input stream, 
buffering characters so as to provide for the efficient reading of characters, arrays, and lines.** */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


/* **Define a public class named Server. This class will contain all the logic for our server program.** */
public class Server {
    
        /* **Define the main method. This is the entry point for any Java application. 
    The Java Virtual Machine (JVM) calls the main method when the program starts.** */

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        /* **Define a final integer variable for the port number. 12345 is used here.
        The server will listen on this port for incoming client connections.** */
        final int PORT = 12345;

         /* **Try to establish a server socket on the specified port number.
        The ServerSocket class constructor takes one parameter: the port number. The try-with-resources statement ensures that the server socket is closed when it is no longer needed.** */

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            /* **Print a message to the console indicating that the server is listening on the specified port.** */
            System.out.println("Sever is Listing to the " + PORT);

            /* **Enter an infinite loop where the server will continuously listen for and accept incoming client connections.** */
            while (true) {

                /* **Accept an incoming client connection. The accept method of the ServerSocket class blocks
                until a connection is made, and then returns a new Socket object representing the client connection.** */
                Socket clientSocket = serverSocket.accept();

                /* **Print a message to the console indicating that a client has connected.
                The getInetAddress method of the Socket class returns the IP address of the client.** */
                System.out.println("Client Is Connected..!" + clientSocket.getInetAddress());

                /* **Create a BufferedReader object for reading incoming messages from the client.
                The BufferedReader class constructor takes an InputStreamReader, which in turn takes the client socket's input stream.** */
                InputStream input = clientSocket.getInputStream();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input))) {

                        /* **Read a message from the client and store it in a string variable.
                        The readLine method of the BufferedReader class reads a line of text from the client.** */
                    String message = bufferedReader.readLine();

                    /* **Print the client's message to the console.** */
                    System.out.println(message);

                    OutputStream output = clientSocket.getOutputStream();

                /* **Create a PrintWriter object for sending messages to the client.
                The PrintWriter class constructor takes two parameters: the client socket's output stream
                and a boolean indicating whether to automatically flush the output stream after every write operation.** */
                    try (PrintWriter writer = new PrintWriter(output, true)) {
                            /* **Send a response back to the client. The println method of the PrintWriter class
                            sends a string followed by a newline to the client.** */
                        writer.println("Server: I got you Client...!");

                            /* **Close the client socket. The close method of the Socket class closes the client socket and
                            releases all associated resources.** */
                        clientSocket.close();

                    } catch (Exception e) {
                            /* **Catch any IOException that may occur and print the stack trace.
                            An IOException is thrown when an input-output operation is failed or interrupted.** */
                        LOGGER.config(e.getLocalizedMessage());
                        clientSocket.close();
                        break;
                    }

                } catch (Exception e) {
                        /* **Catch any IOException that may occur and print the stack trace.
                        An IOException is thrown when an input-output operation is failed or interrupted.** */
                    LOGGER.config(e.getLocalizedMessage());
                    clientSocket.close();
                    break;
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.config(e.getLocalizedMessage());

        }
    }


}

       
