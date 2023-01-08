class Cal{
    int num1;
    int num2;
    public void Calu(int num1 , String operator, int num2){
        switch (operator){
            case "+":
                System.out.println(num1+num2);
                break;
                case "-":
                    System.out.println(num1-num2);
                    break;
            case "*":
                System.out.println(num1*num2);
                break;
            case "/":
                System.out.println(num1/num2);
                break;
        }
    }
}
public class Calc {
    public static void main(String[] args){
        Cal obj = new Cal();
        obj.Calu(2, "-", 5);
    }
}
