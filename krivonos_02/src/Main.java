public class Main {
    public static void main(String[] args) {
        int a = 3, b = 5, c = 7;
        System.out.println(calculateAverageOfThree(a, b, c));
        printMessage();
        System.out.println(multiplyAndSum(a, b));
    }

    static int calculateAverageOfThree(int a, int b, int c) {
        int average = (a + b + c)/3;
        return average;
    }

    static void printMessage() {
        System.out.println("I can do it!");
    }

    static int multiplyAndSum(int a, int b) {
        int result = a;
        result *= b;
        result += a + b;
        return result;
    }
}
