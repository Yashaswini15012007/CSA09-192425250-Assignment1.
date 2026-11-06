# Task 10: Free-Slot Stack

A stack provides O(1) push/pop allocation and release when the exact free slot is known. It trades away the floor-major first-free ordering of a scan unless its initial and returned-slot ordering is managed deliberately. Storage is O(S) for S slots. Compile and run with `javac FreeSlotStackDemo.java` and `java FreeSlotStackDemo`.
