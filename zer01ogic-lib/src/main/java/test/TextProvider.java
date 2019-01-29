package test;

public class TextProvider {
    public static String getHello() {
        return hello;
    }

    public static void setHello(String hello) {
        TextProvider.hello = hello;
    }

    public static String getGoodbye() {
        return goodbye;
    }

    public static void setGoodbye(String goodbye) {
        TextProvider.goodbye = goodbye;
    }

    private static String hello="hello";
    private static String goodbye="goodbye";

}
