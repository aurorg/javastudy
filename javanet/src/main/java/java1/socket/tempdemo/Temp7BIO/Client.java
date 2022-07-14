package java1.socket.tempdemo.Temp7BIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
 public static void main(String[] args) throws Exception {
  Socket client = new Socket("localhost", 2826);

  PrintWriter printWriter = new PrintWriter(client.getOutputStream());
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  while(true) {
   String line = reader.readLine();

   if (null != line) {
    printWriter.println(line);
    printWriter.flush();
   }
  }
 }
}

