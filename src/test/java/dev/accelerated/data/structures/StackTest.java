package dev.accelerated.data.structures;

import net.jqwik.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

enum StackActions {
    PUSH, POP
}

class StackPropertyTests {

    @Provide
    Arbitrary<StackActions> actions() {
        return Arbitraries.of(StackActions.class);
    }

    @Provide
    <T> Arbitrary<Tuple.Tuple2<StackActions, T>> actionValuePair(@ForAll("actions") StackActions action, @ForAll T value) {
        return Arbitraries.just(Tuple.of(action, value));
    }

    @Provide
    <T> Arbitrary<Stack<T>> stacks(@ForAll List<Tuple.@From("actionValuePair") Tuple2<StackActions, T>> actions) {
        Stack<T> stack = new Stack<>();

        // iterate over the actions
        for (Tuple.Tuple2<StackActions, T> action : actions) {
            // if the action is PUSH
            if (action.get1() == StackActions.PUSH) {
                stack.push(action.get2());
            }
            // if the action is POP
            else if (action.get1() == StackActions.POP) {
                stack.pop();
            }
        }

        return Arbitraries.just(stack);
    }

    @Example
    void an_empty_stack_has_len_0() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.len());
    }

    @Example
    void popping_an_empty_stack_returns_nothing() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(Optional.empty(), stack.pop());
    }

    @Example
    void peeking_an_empty_stack_returns_nothing() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(Optional.empty(), stack.peek());
    }

    @Property
    void pushing_a_value_increases_the_len_by_1(@ForAll("stacks") Stack<Integer> stack, @ForAll int value) {
        Integer previousSize = stack.len();
        stack.push(value);
        Integer newSize = stack.len();

        assertEquals(newSize, previousSize + 1);
    }

    @Property
    void popping_a_value_decreases_the_len_by_1_at_most(@ForAll("stacks") Stack<Integer> stack) {
        Integer previousSize = stack.len();
        stack.pop();
        Integer newSize = stack.len();

        assertEquals(newSize, Math.max(previousSize - 1, 0));
    }

    @Property
    void pushing_and_popping_does_not_change_the_size(@ForAll("stacks") Stack<Integer> stack, @ForAll int value) {
        Integer previousSize = stack.len();
        stack.push(value);
        stack.pop();
        Integer newSize = stack.len();

        assertEquals(newSize, previousSize);
    }

    @Property
    void popping_returns_the_last_pushed_value(@ForAll("stacks") Stack<Integer> stack, @ForAll int value) {
        stack.push(value);
        assertEquals(Optional.of(value), stack.pop());
    }

    @Property
    void peeking_returns_the_last_pushed_value(@ForAll("stacks") Stack<Integer> stack, @ForAll int value) {
        stack.push(value);
        assertEquals(Optional.of(value), stack.peek());
    }

    @Property
    void peeking_does_not_change_the_size(@ForAll("stacks") Stack<Integer> stack) {
        Integer previousSize = stack.len();
        stack.peek();
        Integer newSize = stack.len();

        assertEquals(newSize, previousSize);
    }
}