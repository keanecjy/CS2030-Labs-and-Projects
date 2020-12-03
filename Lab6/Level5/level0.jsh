Map<String,Integer> map = new HashMap<>();
map.put("one", 1);
map.put("two", 2);
map.put("three", 3);
map.get("one")
map.get("four")
map.entrySet()
for (Map.Entry e: map.entrySet()) {
	System.out.println(e.getKey() + ":" + e.getValue());
}
