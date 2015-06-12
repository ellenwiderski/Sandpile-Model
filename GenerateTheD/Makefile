CPP = g++
CPPFLAGS = -std=c++11 -g

.o:	$@.cpp $@.h
	$(CPP) $(CPPFLAGS) -c $@.cpp


fast: maybeFaster.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) maybeFaster.cpp HammingDistance.o pair.o -o fast

fixed: fixedword.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) fixedword.cpp HammingDistance.o pair.o -o fixed

d: GenerateTheD.cpp HammingDistance.o pair.o
	$(CPP) $(CPPFLAGS) GenerateTheD.cpp HammingDistance.o pair.o -o d

clean: 
		rm -rf -f *.o d fast fixed *.dSYM