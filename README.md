# require4j

A tiny Java API for clean, expressive validation using custom exceptions.
No boilerplate, no dependencies — just simple, fluent defensive programming.

##  Motivation

Java validation often looks like:

```
if (value == null) {
    throw new IllegalArgumentException("Value cannot be null");
}
if (value.isEmpty() || value.isBlank()) {
    throw new IllegalArgumentException("Value cannot be empty");
}
```
I was tired of repeating this everywhere when doing validations, especially in the service-layer.
require4j makes validation concise and reusable:

```
Require.nonNull(myObject, () -> new IllegalArgument("myObject cant be null"));
Require.nonEmpty(myList, () -> new MyCustomException("myList is empty"));
```

- ✔ Works with any exception (E extends Exception)
- ✔ Simple, extensible, zero dependencies
- ✔ Perfect for domain validation & defensive programming
- ✔ Super cute, might delete later
