package dev.accelerated.data.structures;

import java.util.Optional;

class LinkedList<T> {

    T head;
    Optional<LinkedList<T>> tail;
    int len;

    LinkedList(T head, Optional<LinkedList<T>> tail) {
        this.head = head;
        this.tail = tail;
        this.len = 1 + (tail.isPresent() ? tail.get().len : 0);
    }

    LinkedList<T> push(T value) {
        return new LinkedList<>(value, Optional.of(this));
    }

    PopResult<T> pop() {
        return new PopResult<>(
                head, tail
        );
    }

    T peek() {
        return head;
    }

    int len() {
        return len;
    }
}

class PopResult<T> {
    T poppedValue;
    Optional<LinkedList<T>> updatedStack;

    PopResult(T poppedValue, Optional<LinkedList<T>> updatedStack) {
        this.poppedValue = poppedValue;
        this.updatedStack = updatedStack;
    }
}