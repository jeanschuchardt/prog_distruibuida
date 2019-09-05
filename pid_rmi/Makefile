all:			PidClient.class PidServer.class \
			PidImpl.class PidInterface.class

PidImpl.class:		PidImpl.java PidInterface.class
			@javac PidImpl.java

PidInterface.class:	PidInterface.java
			@javac PidInterface.java

PidClient.class:	PidClient.java
			@javac PidClient.java

PidServer.class:	PidServer.java
			@javac PidServer.java

run:			all
			@java PidServer &
			@sleep 1
			@java PidClient

clean:
			@rm -f *.class *~

info:
			@echo "(c) Roland Teodorowitsch (08 abr. 2015)"

