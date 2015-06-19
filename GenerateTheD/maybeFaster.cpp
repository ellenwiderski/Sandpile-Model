#include <iostream>
#include <string>
#include <math.h>
#include <map>
#include <time.h>
#include <vector>
#include <stdlib.h>
#include <fstream>
#include <assert.h>
#include <algorithm>
using namespace std;

int lcs(string a, string b) {
	cout << a.size() << "x" << b.size() << "___";
	vector<int> old (b.size()+1, 0);
	vector<int> current (a.size()+1, 0);

	for (int i = 0; i < a.size(); i++) {
		for (int j = 0; j < b.size(); j++) {
			if (a[i] == b[j]) {
				current[j+1] = old[j] + 1;
			}
			else {
				current[j+1] = max(old[j+1],current[j]);
			}
		}
		old = current;
		current.assign(a.size(), 0);
	}


	int result = current[a.size()];
	return result;
}

string B(int n) {
	string bn = "0";

	for (int i = 0; i < n; i++) {
		bn = bn + bn + "1" + bn;
	}

	return bn;
}

string cross(string one,string two) {
	string newString = "";
	int first;
	int second;
	for (int i = 0; i < one.size(); i++){
		first = 2 * ((int)(one[i])-48);
		second = ((int)two[i]-48);
		newString += to_string(first + second);
	}
	return newString;
}

pair<string,string> dxd(int maxLength) {

	int bnLength = ceil(0.5529610484 + 0.9220896727*log(maxLength))	+ 4;
	string bn = B(bnLength);

	string rand1a = bn.substr(rand() % (bn.size() - maxLength), maxLength);
	string rand1b = bn.substr(rand() % (bn.size() - maxLength), maxLength);

	string word1 = cross(rand1a,rand1b);

	string rand2a = bn.substr(rand() % (bn.size() - maxLength), maxLength);
	string rand2b = bn.substr(rand() % (bn.size() - maxLength), maxLength);

	string word2 = cross(rand2a,rand2b);

	pair<string,string> words (word1,word2);

	return words;
}


int main(int argc, char** argv) {
	ofstream myfile;

	int wordLen = 1000000;
	int numTests = 333;
	srand(time(NULL));

	myfile.open("lengthOf"+to_string(wordLen)+"Tests"+to_string(numTests)+".csv");

	myfile << "Word Length = ," << wordLen << endl;

	myfile << "Iteration Number, Word length - subsequence length\n";

	for (int i = 0; i < numTests; i++) {
		pair<string,string> words = dxd(wordLen);
		int s = lcs(words.first, words.second);
		myfile << i+1 << ", " <<wordLen - s<< endl;
	}

	myfile.close();
	return 0;
}
