CPP = g++
CPPFLAGS = -std=c++11 -g

.o:	$@.cpp $@.h
	$(CPP) $(CPPFLAGS) -c $@.cpp

d: GenerateTheD.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) GenerateTheD.cpp HammingDistance.o pair.o -o d

clean: rm *.o