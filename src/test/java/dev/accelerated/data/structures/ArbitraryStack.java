//package dev.accelerated.data.structures;
//
//import net.jqwik.api.Arbitraries;
//import net.jqwik.api.Arbitrary;
//import net.jqwik.api.ArbitrarySupplier;
//import net.jqwik.api.Tuple;
//
//import java.util.List;
//
//enum StackActions {
//    PUSH, POP
//}
//
//public class ArbitraryStack<T> implements ArbitrarySupplier<Stack<T>> {
//    @Override
//    public Arbitrary<Stack<T>> get() {
//
//        Arbitrary<List<Integer>> integers = Arbitraries.integers().list().ofMaxSize(100);
//        Arbitrary<List<StackActions>> actions = Arbitraries.of(StackActions.class).list();
//
//        Tuple.Tuple2<List<Integer>, List<StackActions>> tuple = integers.zip(actions).sample();
//
//        return ArbitrarySupplier.super.get();
//    }
//}
