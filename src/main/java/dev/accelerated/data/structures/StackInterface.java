package dev.accelerated.data.structures;

import java.util.Optional;

public interface StackInterface<T> {
    /**
     * Pushes a value onto the stack.
     */
    public void push(T value);

    /**
     * Pops the value that was last added off the stack. This value may
     * or may not be present
     */
    public Optional<T> pop();

    /**
     * Similar to pop, but does not remove the value from the stack, it
     * just returns it, so it can be inspected.
     */
    public Optional<T> peek();

    /**
     * Returns the number of elements currently in the stack.
     */
    public int size();
}
