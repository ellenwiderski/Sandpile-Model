#include "pair.h"
using namespace std;

class LCS {
protected:
	typedef deque<shared_ptr<Pair>> PAIRS;
	typedef deque<uint32_t> THRESHOLD;
	typedef deque<uint32_t> INDEXES;
	typedef map<char, INDEXES> CHAR2INDEXES;
	typedef deque<INDEXES*> MATCHES;

	uint64_t Pairs(MATCHES&, shared_ptr<Pair>*);

	void Match(CHAR2INDEXES&, MATCHES&, const string&, const string&);

	string Select(shared_ptr<Pair>, uint64_t, bool, const string&, const string&);

public:
	string Correspondence(const string&, const string&);

};