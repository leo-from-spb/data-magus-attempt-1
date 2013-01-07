package lb.datamagus.model.core;


import jet.runtime.typeinfo.KotlinSignature;



public final class JavaHelper
{

    @KotlinSignature("fun <T> classForName(fullClassName : String) : Class<T>")
    public static <T> Class<T> classForName(String fullClassName)
    {
        try {
            return (Class<T>) Class.forName(fullClassName);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
