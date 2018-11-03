package com.parassidhu.trigonomania;

class MathUtils {

    public static float[] getTrigonometricValues(float angle, int side){

        angle = (float) Math.toRadians(angle);

        return new float[]{
                getSine(angle),
                getCosine(angle),
                getTangent(angle),
                getCosecant(angle),
                getSecant(angle),
                getCotangent(angle)
        };
    }

    private static float getSine(float angle){
        return (float) Math.sin(angle);
    }

    private static float getCosine(float angle){
        return (float) Math.cos(angle);
    }

    private static float getTangent(float angle){
        return (float) Math.tan(angle);
    }

    private static float getCotangent(float angle){
        return (float) (1.0/Math.tan(angle));
    }

    private static float getSecant(float angle){
        return (float) (1.0/Math.cos(angle));
    }

    private static float getCosecant(float angle){
        return (float) (1.0/Math.sin(angle));
    }
}
