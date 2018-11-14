package com.parassidhu.trigonomania;

public class MathUtils {

    private static final String PERPENDICULAR = "Perpendicular";
    private static final String BASE = "Base";
    private static final String HYPOTENUSE = "Hypotenuse";

    private static double[] makeArray(double value1, double value2, double value3) {
        return new double[]{
                value1, value2, value3
        };
    }

    public static String assignSide(int sideSwitch) {
        if (sideSwitch == 0)
            return "AB";
        else if (sideSwitch == 1)
            return "AC";
        else
            return "BC";
    }

    private static double[] makeArrayOfTrigValues(double perp,
                                                  double base, double hypo) {
        return new double[]{
                getSine(perp,hypo),
                getCosine(base, hypo),
                getTangent(perp, base),
                getCotangent(base, perp),
                getSecant(hypo, base),
                getCosecant(hypo, perp)
        };
    }

    public static String[] trigonometricFunctions = {
            "Sin(\u03B8):",
            "Cos(\u03B8):",
            "Tan(\u03B8):",
            "Cot(\u03B8):",
            "Sec(\u03B8):",
            "Cosec(\u03B8):"
    };

    public static String[] sidesPlaceHolderTheta = {
            "Perpendicular (AB):",
            "Base (BC):",
            "Hypotenuse (AC):"
    };

    public static String[] sidesPlaceHolderPhi = {
            "Perpendicular (BC):",
            "Base (AB):",
            "Hypotenuse (AC):"
    };
    // -----------------------------------------------------------------------------------
    private static double getSine(double angle) { return Math.sin(Math.toRadians(angle)); }

    private static double getCosine(double angle) { return Math.cos(Math.toRadians(angle)); }

    // -----------------------------------------------------------------------------------
    private static double getSine(double perp, double hypo) { return perp / hypo; }

    private static double getCosine(double base, double hypo) { return base / hypo; }

    private static double getTangent(double perp, double base) { return perp / base; }

    private static double getCotangent(double base, double perp) {return base / perp; }

    private static double getSecant(double hypo, double base) { return hypo / base; }

    private static double getCosecant(double hypo, double perp) { return hypo / perp;}
    // -----------------------------------------------------------------------------------

    static double[] performCalculationsForTheta(String side,
                                                double valueOfAngle, double valueOfSide) {
        switch (side) {
            case "AB":
                return triangleCalculation(PERPENDICULAR, valueOfAngle, valueOfSide);
            case "BC":
                return triangleCalculation(BASE, valueOfAngle, valueOfSide);
            default:
                return triangleCalculation(HYPOTENUSE, valueOfAngle, valueOfSide);
        }
    }

    static double[] performCalculationsForPhi(String side,
                                              double valueOfAngle, double valueOfSide) {
        switch (side) {
            case "AB":
                return triangleCalculation(BASE, valueOfAngle, valueOfSide);
            case "BC":
                return triangleCalculation(PERPENDICULAR, valueOfAngle, valueOfSide);
            default:
                return triangleCalculation(HYPOTENUSE, valueOfAngle, valueOfSide);
        }
    }

    // Used for second method
    public static double[] trigonometricCalculations(String side1, String side2,
                                                     double valueOfSide1, double valueOfSide2) {
        String AB = "AB";
        String AC = "AC";
        String BC = "BC";

        Double perp = null;
        Double base = null;
        Double hypo = null;

        if (side1.equals(AB)) {
            perp = valueOfSide1;
            if (side2.equals(AC))
                hypo = valueOfSide2;
            else
                base = valueOfSide2;
        } else if (side1.equals(AC)) {
            hypo = valueOfSide1;
            if (side2.equals(AB))
                perp = valueOfSide2;
            else
                base = valueOfSide2;
        } else {
            base = valueOfSide1;
            if (side2.equals(AB))
                perp = valueOfSide2;
            else
                hypo = valueOfSide2;
        }

        if (perp == null)
            perp = calculateSideOfTriangle(null, base, hypo);
        else if (base == null)
            base = calculateSideOfTriangle(perp, null, hypo);
        else
            hypo =calculateSideOfTriangle(perp, base, null);


        return makeArrayOfTrigValues(perp, base, hypo);
    }

    // Create array with elements in order:
    // Perpendicular, Base and Hypotenuse
    private static double[] triangleCalculation(String sideName, double valueOfAngle, double valueOfSide) {
        if (sideName.equals(PERPENDICULAR)) {
            double hypo = valueOfSide / getSine(valueOfAngle);
            return makeArray(valueOfSide, calculateSideOfTriangle(valueOfSide, null, hypo), hypo);
        } else if (sideName.equals(BASE)) {
            double hypo = valueOfSide / getCosine(valueOfAngle);
            return makeArray(calculateSideOfTriangle(null, valueOfSide, hypo), valueOfSide, hypo);
        } else {
            double perp = valueOfSide * getSine(valueOfAngle);
            return makeArray(perp, calculateSideOfTriangle(perp, null, valueOfSide), valueOfSide);
        }
    }

    // Given two sides, calculate the third
    private static double calculateSideOfTriangle(Double perp, Double base, Double hypo) {
        if (hypo == null)
            return Math.sqrt((perp * perp) + (base * base));
        else if (perp == null)
            return Math.sqrt((hypo * hypo) - (base * base));
        else
            return Math.sqrt((hypo * hypo) - (perp * perp));
    }
}
