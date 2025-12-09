package se.require4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Require {

    private final Object object;
    private final List<String> exceptionMessages;

    private Require(Object object) {
        this.object = object;
        this.exceptionMessages = new ArrayList<>();
    }

    public static Require of(Object obj) {
        return new Require(obj);
    }

    public Require nonNull(String errorMsg) {
        if(object == null) {
            exceptionMessages.add(errorMsg);
        }
        return this;
    }

    public Require nonEmpty(String errorMsg) {
        if (isEmpty(object)) {
           exceptionMessages.add(errorMsg);
        }
        return this;
    }

    public Require state(boolean state, String errorMsg) {
        if(!state) {
            exceptionMessages.add(errorMsg);
        }
        return this;
    }

    public <E extends Exception> void orThrows(Function<String, E> exceptionSupplier) throws E {
        if (exceptionMessages.isEmpty()) {
            return;
        }
        throw exceptionSupplier.apply(exceptionMessages.getFirst());
    }

    public static <E extends Exception> void state(boolean state, Supplier<E> exceptionSupplier) throws E {
        if(!state) {
            throw exceptionSupplier.get();
        }
    }

    public static <E extends Exception> void nonNull(Object obj, Supplier<E> exceptionSupplier) throws E {
        if(obj == null) {
            throw exceptionSupplier.get();
        }
    }

    public static <E extends Exception> void nonEmpty(Object obj, Supplier<E> exceptionSupplier) throws E {
        if(isEmpty(obj)) {
            throw exceptionSupplier.get();
        }
    }

    private static boolean isEmpty(Object obj) {
        return switch(obj) {
            case null -> true;
            case String str when Utils.isEmpty(str) -> true;
            case Collection<?> collection when collection.isEmpty() -> true;
            case Map<?,?> map when map.isEmpty() -> true;
            case byte[] byteArray when byteArray.length == 0 -> true;
            case int [] intArray when intArray.length == 0 -> true;
            case long [] longArray when longArray.length == 0 -> true;
            case double [] doubleArray when doubleArray.length == 0 -> true;
            case float [] floatArray when floatArray.length == 0 -> true;
            case char [] charArray when charArray.length == 0 -> true;
            case boolean [] booleanArray when booleanArray.length == 0 -> true;
            case short [] shortArray when shortArray.length == 0 -> true;
            case Object[] array when array.length == 0 -> true;
            default -> false;
        };
    }
}
