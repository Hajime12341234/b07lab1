public class Driver {
public static void main(String [] args) {
    // testing for multiply function
    // first test
    double[] test1_coef1 = {1, 2};
    int[] test1_exp1 = {0, 1};
    double[] test1_coef2 = {3, 4};
    int[] test1_exp2 = {0, 1};
    Polynomial test1_poly1 = new Polynomial(test1_coef1, test1_exp1);
    Polynomial test1_poly2 = new Polynomial(test1_coef2, test1_exp2);
    Polynomial product = test1_poly1.multiply(test1_poly2);
    System.out.println("Product 1: " + product.toString());

    // second test
    double[] test2_coef1 = {1, 2};
    int[] test2_exp1 = {0, 1};
    double[] test2_coef2 = {3, -4, 5};
    int[] test2_exp2 = {0, 2, 3};
    Polynomial test2_poly1 = new Polynomial(test2_coef1, test2_exp1);
    Polynomial test2_poly2 = new Polynomial(test2_coef2, test2_exp2);
    Polynomial product2 = test2_poly1.multiply(test2_poly2);
    System.out.println("Product 2: " + product2.toString());

}





}