package dev.accelerated.data.structures;

import java.util.Optional;

public class Stack<T> implements StackInterface<T> {

    private Optional<LinkedList<T>> data = Optional.empty();

    @Override
    public void push(T value) {
        this.data = this.data
                .map(tLinkedList -> tLinkedList.push(value))
                .or(() -> Optional.of(new LinkedList<>(value, Optional.empty())));
    }

    @Override
    public Optional<T> pop() {
        if (data.isEmpty()) {
            return Optional.empty();
        } else {
            PopResult<T> popResult = data.get().pop();
            data = popResult.updatedStack;
            return Optional.of(popResult.poppedValue);
        }
    }

    @Override
    public Optional<T> peek() {
        return data.map(LinkedList::peek);
    }

    @Override
    public int len() {
        return data.map(x -> x.len()).orElse(0);
    }
}
