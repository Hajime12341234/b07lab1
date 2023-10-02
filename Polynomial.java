import java.util.Arrays;
import java.io.*;
public class Polynomial {
    private double[] non_zero_coefficients;
    private int[] exponents;

    public Polynomial(double[] non_zero_coef, int[] exponents) {
        this.non_zero_coefficients = new double[non_zero_coef.length];
        for (int i=0; i < non_zero_coef.length; i++){
            this.non_zero_coefficients[i] = non_zero_coef[i];
        }

        this.exponents = new int[exponents.length];
        for (int j=0; j < exponents.length; j++){
            this.exponents[j] = exponents[j];
        }
    }


    public Polynomial multiply(Polynomial input){
        int new_length = this.non_zero_coefficients.length * input.non_zero_coefficients.length;
        double[] updated_coefficients = new double[new_length];
        int[] updated_exponents = new int[new_length];

        int count_index = 0;

        for (int i=0; i < this.non_zero_coefficients.length; i++) {
            for (int j=0; j < input.non_zero_coefficients.length; j++) {
                double temp_coef = this.non_zero_coefficients[i] * input.non_zero_coefficients[j];
                int temp_exp = this.exponents[i] + input.exponents[j];

                int contained = 0;
                for (int k=0; k < count_index; k++){
                    if (updated_exponents[k] == temp_exp){
                        updated_coefficients[k] += temp_coef;
                        contained = 1;
                        break;
                    }
                }

                if (contained == 0) {
                    updated_coefficients[count_index] = temp_coef;
                    updated_exponents[count_index] = temp_exp;
                    count_index++;
                }
            }
        }

        return new Polynomial(updated_coefficients, updated_exponents);
    }


    public Polynomial(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        String[] terms = line.split("(?=[+-])");

        double[] coefficients = new double[terms.length];
        int[] exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];
            String[] parts = term.split("x");

            if (parts.length == 1) {
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = 0; 
            } else {
                if (parts[0].isEmpty()) {
                    coefficients[i] = 1;
                } else if (parts[0].equals("-")) {
                    coefficients[i] = -1;
                } else {
                    coefficients[i] = Double.parseDouble(parts[0]);
                }
                if (parts[1].isEmpty()) {
                    exponents[i] = 1;
                } else {
                    exponents[i] = Integer.parseInt(parts[1].substring(1)); 
                }
            }
        }
        this.non_zero_coefficients = coefficients;
        this.exponents = exponents;
    }


    public void saveToFile(String file_name) throws IOException {
        FileWriter file_writer = new FileWriter(file_name);
        PrintWriter print_writer = new PrintWriter(file_writer);

        boolean first_term = true;

        for (int i = 0; i < non_zero_coefficients.length; i++) {
            if (non_zero_coefficients[i] != 0) {
                String operator;
                if (non_zero_coefficients[i] > 0) {
                    operator = "+";
                } else {
                    operator = "-";
                }
                double coefficient = Math.abs(non_zero_coefficients[i]);
                int exponent = exponents[i];

                String term;
                if (exponent == 0) {
                    term = String.valueOf(non_zero_coefficients[i]);
                } else if (exponent == 1) {
                    term = coefficient + "x";
                } else {
                    term = coefficient + "x^" + exponent;
                }

                if (!first_term) {
                    print_writer.print(operator);
                }
                print_writer.print(term);


                first_term = false;
            }
        }

        print_writer.close();
        file_writer.close();
    }
}
