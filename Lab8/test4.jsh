/open InfiniteList.java
/open InfiniteListImpl.java
InfiniteList.iterate(0, x -> x + 1).limit(0).count()
InfiniteList.iterate(0, x -> x + 1).limit(1).count()
InfiniteList.iterate(0, x -> x + 1).filter(x -> x % 2 == 1).limit(10).count()
InfiniteList.iterate(0, x -> x + 1).limit(10).filter(x -> x % 2 == 1).count()
InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).count()
InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).filter(x -> x % 2 == 0).count()
InfiniteList.iterate(0, x -> x + 1).limit(5).reduce(0, (x, y) -> x + y)
InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y)
InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce(0, (x, y) -> x + y)
InfiniteList.iterate(0, x -> x + 1).limit(5).reduce((x, y) -> x + y)
InfiniteList.iterate(0, x -> x + 1).limit(0).reduce((x, y) -> x + y)
InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce((x, y) -> x + y)
Random rnd = new Random(1)
InfiniteList.generate(() -> Math.abs(rnd.nextInt()) % 100).takeWhile(x -> x > 5).count()
Random rnd = new Random(1)
InfiniteList.generate(() -> rnd.nextInt() % 100).filter(x -> x > 0).limit(10).reduce(0, (x, y) -> Math.max(x, y))
/exit
