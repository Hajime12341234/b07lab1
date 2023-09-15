public class Polynomial {
    private double[] coefficients; 
    public Polynomial() {
        this.coefficients = new double[]{0};
    }
    
    public Polynomial(double[] coefficients) {
        this.coefficients = new double[coefficients.length];
        int i; 
        for (i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    private int big(int a, int b) {
        if (a>b){
            return a; 
        } else{
            return b;
        }
    }
    public Polynomial add(Polynomial input_vector) {
        int valid_length = big(this.coefficients.length, input_vector.coefficients.length);
        double[] result_poly = new double[valid_length];

        for (int i = 0; i < this.coefficients.length; i++) {
            result_poly[i] += this.coefficients[i];
        }

        for (int i = 0; i < input_vector.coefficients.length; i++) {
            result_poly[i] += input_vector.coefficients[i];
        }

        return new Polynomial(result_poly);
    }
    private double power(double base, double num, double partial){
        if (num == 0) return partial; 
        else{
            return power(base, num - 1, partial * base); 
        }
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * power(x, i, 1.0); 
        }
        return result;
    }

    private double abs_value(double input){
        if (input < 0){
            return -1*input; 
        }
        else{ 
            return input; 
        }
    }

    public boolean hasRoot(double x) {
        double absolute = abs_value(evaluate(x)); 
        double epsilon = 1e-9; 
        return absolute < epsilon; 
    }
}
