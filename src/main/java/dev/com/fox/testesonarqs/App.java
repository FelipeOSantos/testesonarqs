package dev.com.fox.testesonarqs;

/**
 * Hello world!
 *
 */
public class App 
{	
    public static void main( String[] args )
    {
    	String msg = new String();
        Hello hello = new Hello();
        
        msg.concat(hello.getMsg());
    }
}