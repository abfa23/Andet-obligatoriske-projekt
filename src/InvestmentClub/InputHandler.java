package InvestmentClub;

import Entities.Portfolio;

public class InputHandler {

    //tjekker om User har nok penge.
    public static boolean validateEnoughCash(Portfolio p, double amount) {
        double currentCash = p.getBalance();
        return currentCash >= amount;
    }

    //tjekker om Int nummer er brugt.
    public static boolean ValidateInputIsInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //tjekker om Double nummer er brugt.
    public static boolean ValidateInputIsDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            return  false;
        }
    }

}
