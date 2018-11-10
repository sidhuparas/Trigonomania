package com.parassidhu.trigonomania;

class MathUtils {

    private static final String PERPENDICULAR = "Perpendicular";
    private static final String BASE = "Base";
    private static final String HYPOTENUSE = "Hypotenuse";

    private static double[] makeArray(double value1, double value2, double value3,
                                      double value4, double value5, double value6) {
        return new double[]{
                value1, value2, value3, value4, value5, value6
        };
    }

    private static double[] makeArray(double value1, double value2, double value3) {
        return new double[]{
                value1, value2, value3
        };
    }

    static String[] trigonometricFunctions = {
            "Sin(\u03B8)",
            "Cos(\u03B8)",
            "Tan(\u03B8)",
            "Cosec(\u03B8)",
            "Sec(\u03B8)",
            "Cot(\u03B8)"
    };

    static String[] sidesPlaceHolderTheta = {
            "Perpendicular (AB):",
            "Base (BC):",
            "Hypotenuse (AC):"
    };

    static String[] sidesPlaceHolderPhi = {
            "Perpendicular (BC):",
            "Base (AB):",
            "Hypotenuse (AC):"
    };

    private static double getSine(double angle) { return Math.sin(Math.toRadians(angle)); }

    private static double getCosine(double angle) { return Math.cos(Math.toRadians(angle)); }

    private static double getTangent(double angle) { return Math.tan(Math.toRadians(angle)); }

    private static double getCotangent(double angle) { return (1.0 / Math.tan(Math.toRadians(angle))); }

    private static double getSecant(double angle) { return (1.0 / Math.cos(Math.toRadians(angle))); }

    private static double getCosecant(double angle) { return (1.0 / Math.sin(Math.toRadians(angle))); }

    static double[] performCalculationsForTheta(String side,
                                                double valueOfAngle, double valueOfSide) {
        switch (side) {
            case "AB":
                return triangleCalculation(PERPENDICULAR, valueOfAngle, valueOfSide);
            case "BC":
                return triangleCalculation(BASE, valueOfAngle, valueOfSide);
            case "AC":
                return triangleCalculation(HYPOTENUSE, valueOfAngle, valueOfSide);
        }
        return makeArray(1,1,1,1,1,1);
    }

    static double[] performCalculationsForPhi(String side,
                                              double valueOfAngle, double valueOfSide) {
        switch (side) {
            case "AB":
                return triangleCalculation(BASE, valueOfAngle, valueOfSide);
            case "BC":
                return triangleCalculation(PERPENDICULAR, valueOfAngle, valueOfSide);
            case "AC":
                return triangleCalculation(HYPOTENUSE, valueOfAngle, valueOfSide);
        }
        return makeArray(1,1,1,1,1,1);
    }

    public static double[] trigonometricCalculations(String side1, String side2,
                                                     double valueOfSide1, double valueOfSide2){
        String AB = "AB";
        String AC = "AC";
        String BC = "BC";

        if (!side1.equals(AC) && !side2.equals(AC))
            return new double[]{calculateSideOfTriangle(valueOfSide1, valueOfSide2, null)};

        return new double[]{1};
    }

    // Create array with elements in order:
    // Perpendicular, Base and Hypotenuse
    private static double[] triangleCalculation(String sideName, double valueOfAngle, double valueOfSide) {
        if (sideName.equals(PERPENDICULAR)) {
            double hypo = valueOfSide / getSine(valueOfAngle);
            return makeArray(valueOfSide, calculateSideOfTriangle(valueOfSide, null, hypo), hypo);
        }else if (sideName.equals(BASE)){
            double hypo = valueOfSide / getCosine(valueOfAngle);
            return makeArray(calculateSideOfTriangle(null, valueOfSide, hypo), valueOfSide, hypo);
        } else {
            double perp = valueOfSide * getSine(valueOfAngle);
            return makeArray(perp, calculateSideOfTriangle(perp, null, valueOfSide), valueOfSide);
        }
    }

    // Given two sides, calculate the third
    private static double calculateSideOfTriangle(Double perp, Double base, Double hypo){
        if (hypo == null)
            return Math.sqrt((perp*perp) + (base*base));
        else if (perp == null)
            return Math.sqrt((hypo*hypo) - (base*base));
        else
            return Math.sqrt((hypo*hypo) - (perp*perp));
    }
}
