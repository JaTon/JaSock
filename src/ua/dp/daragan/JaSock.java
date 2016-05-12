package ua.dp.daragan;
/*
*Программа слушает порт 8080 на localhost и
*записывает первые 3 входящие обращения в файл log.txt
*для проверки нужно пройти в браузере по ссылке http://127.0.0.1:8080/
*/
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JaSock {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        File f = new File("log.txt");
        InputStream in = null;
        PrintWriter pr = null;
        int counter = 0;
        
        try{
            ss = new ServerSocket(8080);
        } catch (IOException e){
            System.err.println(e.getStackTrace());
        }
        
        while (counter < 3){
            counter++;
            try{
                s = ss.accept();
                in = s.getInputStream();
                Scanner sc = new Scanner(in);
                pr = new PrintWriter(new FileOutputStream(f, true), true);
                while(sc.hasNextLine()){
                    pr.println(sc.nextLine());
                }
                pr.println();
            } catch (IOException e){
                System.err.println(e.getStackTrace());
            } finally {
                try{
                    if(in != null) in.close(); //закрываем InputStream , если открыт
                    if(s != null) s.close();//закрываем Socket, если открыт
                    if(pr != null) pr.close();//закрываем PrintWriter, если открыт
                }
                catch (IOException ioe) {
                    System.err.println(ioe.getStackTrace());
                }
            }
        }
        try{
            ss.close();
        }catch (IOException ioe) {
            System.err.println(ioe.getStackTrace());
        }
    }
}