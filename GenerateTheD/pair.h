#include <stdint.h>
#include <string>
#include <memory>                       // for shared_ptr<>
#include <iostream>
#include <deque>
#include <map>
#include <algorithm>                    // for lower_bound()
using namespace std;

class Pair {
public:
	uint32_t index1;
    uint32_t index2;
    shared_ptr<Pair> next;
 
    Pair(uint32_t, uint32_t, shared_ptr<Pair>);

    static shared_ptr<Pair> Reverse(const shared_ptr<Pair>);
};