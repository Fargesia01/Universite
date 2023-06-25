- Astro.ino:
	- It is the main file of the project, in there we setup everything (Datastructure, threads, client etc..) and launch the code that will loop infinitely.
	- There are two processes, the main one handles the client and the wifi, the second one is a thread that registers and stores the sensor value in the datastructure correctly. The reads and writes are protected by a mutex to avoid errors. We use a separate thread to get the sensor values so we can be sure it is sampled every second precisely, and is not delayed by client errors.

- client.cpp/h:
	- Contain function related to the client and the wifi management. They also take care of pushing the data correctly.

- Datastructure.cpp/h
	- Contain everything needed to implement and use the datastructure we created. Also handles the memory correctly. We use the least amount of memory at all time as every new value is allocated to the minimal size needed. If the array is full, free the previous allocated index and then reallocate the needed size.

- utility.cpp/h:
	- Contain utility functions, like setups or print functions

- test.cpp:
	- Launch the test we implemented for our datastructure, returns 1 if a test went wrong.

- Makefile:
	- Regular Makefile to build the test file, contains the following additionnal rules: re, clean, fclean.
