CPP = g++
CPPFLAGS = -std=c++11 -g

.o:	$@.cpp $@.h
	$(CPP) $(CPPFLAGS) -c $@.cpp

fixed: fixedword.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) fixedword.cpp HammingDistance.o pair.o -o fixed

d: GenerateTheD.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) GenerateTheD.cpp HammingDistance.o pair.o -o d

clean: 
		rm -f *.o d