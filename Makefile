LIB = lib/
BIN = out/production/JGraphT-GSoC2018-WarmUp/
SRC = src/

JGT_CORE = $(LIB)jgrapht-core-1.1.1-20180219.093426-27.jar
JGT_DEMO = $(LIB)jgrapht-demo-1.1.1-20180219.093438-27.jar
JGT_EXT = $(LIB)jgrapht-ext-1.1.1-20180219.093434-27.jar
JGT_IO = $(LIB)jgrapht-io-1.1.1-20180219.093431-27.jar

.phony: clean all

all:
	make compile
	make run ${ARGS}

compile:
	javac -Xlint -cp $(JGT_DEMO) -d $(BIN) $(SRC)org/jgrapht/gsoc18/warmup/Main.java

run:
	java -cp $(BIN):$(JGT_DEMO) org.jgrapht.gsoc18.warmup.Main ${ARGS}

clean:
	rm -rf $(BIN)* *.class
