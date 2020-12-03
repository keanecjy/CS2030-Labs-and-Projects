InfiniteList<Integer> list;
UnaryOperator<Integer> op = x -> { System.out.printf("iterate: %d -> %d\n", x, x + 1); return x + 1; };
Predicate<Integer> lessThan5 = x -> { System.out.printf("takeWhile: %d -> %b\n", x, x < 5); return x < 5; }
list = InfiniteList.iterate(1, op).takeWhile(lessThan5).peek().peek()

/exit
