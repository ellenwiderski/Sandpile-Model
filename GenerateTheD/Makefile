CPP = g++
CPPFLAGS = -std=c++11 -g

.o:	$@.cpp $@.h
	$(CPP) $(CPPFLAGS) -c $@.cpp


fast: maybeFaster.cpp
	$(CPP) $(CPPFLAGS) maybeFaster.cpp -o fast

lil: littlestrings.cpp
	$(CPP) $(CPPFLAGS) littlestrings.cpp -o lil

fixed: fixedword.cpp
	$(CPP) $(CPPFLAGS) fixedword.cpp -o fixed

d: GenerateTheD.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) GenerateTheD.cpp HammingDistance.o pair.o -o d

clean:
		rm -rf -f *.o d fast fixed *.dSYM
