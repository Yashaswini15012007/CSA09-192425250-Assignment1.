# Task 09: Lookup Benchmark

For 50,000 entries and 2,000 lookups in this measured run, ArrayList lookup took 547,235,600 ns and HashMap lookup took 1,211,400 ns; both checksums were 99,998,000. ArrayList search is O(N) per lookup, while HashMap lookup is expected O(1), with O(N) storage for either collection. Timings are a single-run observation and vary by JVM and machine.

Run with `javac LookupBenchmark.java` and `java LookupBenchmark`.
