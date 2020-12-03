/open InfiniteList.java
/open InfiniteListImpl.java
Class.forName("InfiniteList").isInterface()
java.lang.reflect.Modifier.isAbstract(Class.forName("InfiniteListImpl").getModifiers())

InfiniteList<String> dots = InfiniteList.generate(() -> ".");
dots instanceof InfiniteListImpl
dots.getClass().isAnonymousClass()
dots.get()
dots.get()
dots.get()
InfiniteList<Integer> even = InfiniteList.iterate(0, i -> i + 2);
even.get()
even.get()
even.get()

Supplier<List<Integer>> randListSupplier = () -> List.of(new Random().nextInt());
InfiniteList<List<? extends Number>> list = InfiniteList.generate(randListSupplier);

Random rnd = new Random(1)
InfiniteList<Integer> ifl = InfiniteList.generate(() -> rnd.nextInt() % 100)
ifl.get()
ifl.get()
IntStream.range(10, 20).forEach(x -> System.out.print(ifl.get() + " "));
/exit
