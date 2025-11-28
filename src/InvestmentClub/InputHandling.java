package InvestmentClub;

import Objects.User;



public class InputHandling {

    //tjekker om User har nok penge.
    public static boolean validateEnoughCash(User user, double amount) {
        double curretCash = user.getInitialCash();
        return curretCash >= amount;
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
