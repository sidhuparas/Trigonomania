package com.parassidhu.trigonomania;

import android.util.Log;

class MathUtils {

    public static double[] makeArray(double value1, double value2, double value3,
                                     double value4, double value5, double value6) {
        return new double[]{
                value1, value2, value3, value4, value5, value6
        };
    }

    private static double getSine(double angle) { return Math.sin(Math.toRadians(angle)); }

    private static double getCosine(double angle) { return Math.cos(Math.toRadians(angle)); }

    private static double getTangent(double angle) { return Math.tan(Math.toRadians(angle)); }

    private static double getCotangent(double angle) { return (1.0 / Math.tan(Math.toRadians(angle))); }

    private static double getSecant(double angle) { return (1.0 / Math.cos(Math.toRadians(angle))); }

    private static double getCosecant(double angle) { return (1.0 / Math.sin(Math.toRadians(angle))); }

    public static double[] performCalculationsForTheta(String side,
                                                       double valueOfAngle, double valueOfSide) {
        switch (side) {
            case "AB":
                triangleCalculation("Perpendicular", valueOfAngle, valueOfSide);
                break;
        }
        return makeArray(1,1,1,1,1,1);
    }

    public static double[] performCalculationsForPhi(String side,
                                                     double valueOfAngle, double valueOfSide) {

        return makeArray(1,1,1,1,1,1);
    }

    static void triangleCalculation(String sideName, double valueOfAngle, double valueOfSide) {
        if (sideName.equals("Perpendicular")) {
            double hypo = valueOfSide / getSine(valueOfAngle);
            Log.d("Hypotenuse:", "triangleCalculation: " + calculateSideOfTriangle(valueOfSide, null, hypo));
        }
    }

    private static double calculateSideOfTriangle(Double perp, Double base, Double hypo){
        if (hypo == null)
            return Math.sqrt((perp*perp) + (base*base));
        else if (perp == null)
            return Math.sqrt((hypo*hypo) - (base*base));
        else
            return Math.sqrt((hypo*hypo) - (perp*perp));
    }
}
