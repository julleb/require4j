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
            case String str when str.isEmpty() -> throw exceptionSupplier.get();
            case Collection<?> collection when collection.isEmpty() -> throw exceptionSupplier.get();
            case Map<?,?> map when map.isEmpty() -> throw exceptionSupplier.get();
            case Object[] array when array.length == 0 -> throw exceptionSupplier.get();
            default -> Require.NonNull(obj, exceptionSupplier);
        }
    }
}
