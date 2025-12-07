package se.require4j;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public class Require {


    public static void NonNull(Object obj, Supplier<RuntimeException> exceptionSupplier) {
        if(obj == null) {
            throw exceptionSupplier.get();
        }
    }

    public static void NonEmpty(Object obj, Supplier<RuntimeException> exceptionSupplier) {
        switch(obj) {
            case null -> throw exceptionSupplier.get();
            case String str when str.isEmpty() -> throw exceptionSupplier.get();
            case Collection<?> collection when collection.isEmpty() -> throw exceptionSupplier.get();
            case Map<?,?> map when map.isEmpty() -> throw exceptionSupplier.get();
            case byte[] byteArray when byteArray.length == 0 -> throw exceptionSupplier.get();
            case int [] intArray when intArray.length == 0 -> throw exceptionSupplier.get();
            case long [] longArray when longArray.length == 0 -> throw exceptionSupplier.get();
            case double [] doubleArray when doubleArray.length == 0 -> throw exceptionSupplier.get();
            case float [] floatArray when floatArray.length == 0 -> throw exceptionSupplier.get();
            case char [] charArray when charArray.length == 0 -> throw exceptionSupplier.get();
            case boolean [] booleanArray when booleanArray.length == 0 -> throw exceptionSupplier.get();
            case short [] shortArray when shortArray.length == 0 -> throw exceptionSupplier.get();
            case Object[] array when array.length == 0 -> throw exceptionSupplier.get();
            default -> Require.NonNull(obj, exceptionSupplier);
        }
    }
}
