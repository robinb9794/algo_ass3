all:: sample_pipe sample_socket

sample_pipe: sample_pipe.o common.o
	$(CC) -g -o $@ sample_pipe.o common.o

sample_socket: sample_socket.o common.o
	$(CC) -g -o $@ sample_socket.o common.o

%.o: %.c
	$(CC) -c -g -o $@ $<

clean::
	rm -f log sample_socket sample_pipe sample_socket.o sample_pipe.o common.o

distclean:: clean
	rm -f *~
