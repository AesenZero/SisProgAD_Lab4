/*Взаємодія потоків. Паралелізм. Обчислити f(x) || g(x), використовуючи 2 допоміжні
потоки (threads): один обчислює f(x), а інший – g(x). Основна програма виконує ввод-
вивід та операцію ||.*/
import java.util.*;

class Fthread extends Thread {
    @Override
    public void run()
    {
        if(Thread.interrupted())return;
        Starter.fx = f(Starter.x);
    }

    public boolean f(boolean x)
    {
        return x;
    }
}

class Gthread extends Thread {
    @Override
    public void run()
    {   
        if(Thread.interrupted())return; 
        Starter.gx = g(Starter.x);
    }

    public boolean g(boolean x)
    {
        do
        {

        }while(true);
    }
}

public class Starter
{
    static Fthread fT;
    static Gthread gT;
    public static boolean x;
    public static boolean fx = false;
    public static boolean gx = false;

    public static void main(String[] args)
    {
        fT = new Fthread();
        gT = new Gthread();
        float temp = 0;
        boolean isStop = false;
        Scanner S = new Scanner(System.in);
        try{
        System.out.println("Input the x {true, false}");
        x = S.nextBoolean();
        }catch(InputMismatchException ime)
        {
            System.out.println("Wrong input");
            return;
        }
        fT.setDaemon(true);
        gT.setDaemon(true);
        fT.start();
        gT.start();
        do
        {   if(!fT.isAlive()&&!gT.isAlive())break;
            if(fT.isInterrupted()||gT.isInterrupted()) break;
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){}
            temp+=0.011;
            if(temp>=10)
            {
                temp=0;
                System.out.println("Do you want to:\n1)Continue waiting\n2)Stop the program\n3)Continue by interupting");
                String s = S.next();
                char c = s.charAt(0);
                switch (c)
                {
                    case '1':
                    {

                    }break;

                    case '2':
                    {
                        fT.interrupt();
                        gT.interrupt();
                        isStop = true;

                    }break;

                    case '3':
                    {
                        fT.interrupt();
                        gT.interrupt();
                    }break;

                    default:
                    {
                        System.out.println("Wrong input, program will stop");
                        fT.interrupt();
                        gT.interrupt();
                        isStop = true;

                    }break;
                }
            }
        }
        while(true);
        if(isStop)return;
        if(fx||gx) System.out.println("Result: true");
        else System.out.println("Result: false");
        return;
        
    }
}