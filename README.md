# JGraphT-GSoC2018-WarmUp
GSoC 2018 - JGraphT warmup exercise to familiarize with JGraphT library (can be found <a href="https://github.com/jgrapht/jgrapht/wiki/GSOC-2018-Warmup">here</a>).

### Compile and run
Compile with: `make compile`  
Run with: `make run ARGS="<input_file> <person1> <person2>"`  
All together: `make ARGS="<input_file> <person1> <person2>"`  

Example: `make AGRS="./input/GOT.dot Jon Daenerys"`

### NaiveLCAFinder - findLcas method behavior
The method seems to work only on DAGs. Issue disscussed <a href="https://github.com/jgrapht/jgrapht/issues/490">here</a>.
