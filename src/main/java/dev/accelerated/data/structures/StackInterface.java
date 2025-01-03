package dev.accelerated.data.structures;

import java.util.Optional;

public interface StackInterface<T> {
    /**
     * Pushes a value onto the stack.
     */
    void push(T value);

    /**
     * Pops the value that was last added off the stack. This value may
     * or may not be present
     */
    Optional<T> pop();

    /**
     * Similar to pop, but does not remove the value from the stack, it
     * just returns it, so it can be inspected.
     */
    Optional<T> peek();

    /**
     * Returns the number of elements currently in the stack.
     */
    int size();
}
