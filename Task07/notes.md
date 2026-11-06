# Task 07: Arrays and Objects Benchmark

Both representations process 100,000 records for 20 rounds and produce checksum 29,998,500. This run measured parallel arrays at 7,410,500 ns and objects at 6,358,600 ns. One run is illustrative, not statistically conclusive: JIT warmup, heap layout, and machine load affect timings. Both representations use O(N) space and O(N) per complete scan.

Run with `javac RepresentationBenchmark.java` and `java RepresentationBenchmark`.
