class Main {
    public static void main(String[] args) {
        Box<Box<Integer>> c = Box.of(Box.of(5));
        Box<Box<Integer>> b = Box.of(Box.of(5));
        System.out.println(b.equals(c)); 
    }
}
