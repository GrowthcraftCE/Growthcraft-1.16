package growthcraft.lib.util;

public class TranslationUtils {

    public static String intToRomanNumeral(int i) {
        String result;
        StringBuilder romanNumeral = new StringBuilder();

        if (i < 1 || i > 3999) {
            result = romanNumeral.toString();
        } else {
            while (i >= 1000) {
                romanNumeral.append("M");
                i -= 1000;
            }
            while (i >= 900) {
                romanNumeral.append("CM");
                i -= 900;
            }
            while (i >= 500) {
                romanNumeral.append("D");
                i -= 500;
            }
            while (i >= 400) {
                romanNumeral.append("CD");
                i -= 400;
            }
            while (i >= 100) {
                romanNumeral.append("C");
                i -= 100;
            }
            while (i >= 90) {
                romanNumeral.append("XC");
                i -= 90;
            }
            while (i >= 50) {
                romanNumeral.append("L");
                i -= 50;
            }
            while (i >= 40) {
                romanNumeral.append("XL");
                i -= 40;
            }
            while (i >= 10) {
                romanNumeral.append("X");
                i -= 10;
            }
            while (i >= 9) {
                romanNumeral.append("IX");
                i -= 9;
            }
            while (i >= 5) {
                romanNumeral.append("V");
                i -= 5;
            }
            while (i >= 4) {
                romanNumeral.append("IV");
                i -= 4;
            }
            while (i >= 1) {
                romanNumeral.append("I");
                i -= 1;
            }
            result = romanNumeral.toString();
        }

        return result;
    }

}
