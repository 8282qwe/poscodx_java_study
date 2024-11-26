package structural.decorator;

public class Client {

    public static void main(String[] args) {

        System.out.println(new ConcreteComponent("Hello World").operation());

        ParenthesesDecorator pd = new ParenthesesDecorator(new ConcreteComponent("Hello World"));
        System.out.println(pd.operation());

        BraceDecorator bd1 = new BraceDecorator(new ConcreteComponent("Hello World"));
        System.out.println(bd1.operation());

        BraceDecorator bd2 = new BraceDecorator(new ParenthesesDecorator(new ConcreteComponent("Hello World")));
        System.out.println(bd2.operation());
    }
}
