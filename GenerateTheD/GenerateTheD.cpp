#include <iostream>
#include <string>
#include <math.h>
#include <map>
#include <time.h>
#include <vector>
#include <stdlib.h>
#include "HammingDistance.h"
using namespace std;

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

map<string,bool> dxd(int maxLength) {
	map<string,bool> dictionary;

	int bnLength = ceil(0.5529610484 + 0.9220896727*log(maxLength));
	string bn = B(bnLength);

	map<string,bool> temp;

	int j = 0;

	while (j < bn.size() - maxLength && temp.size() < 2 * maxLength - 1) {
		string key = bn.substr(j, maxLength);
		temp[key] = false;
		j++;
	}

	for(auto const &s : temp) {
		for(auto const &t : temp) {
			dictionary[cross(s.first,t.first)] = false;

		}
	}

	return dictionary;
}


int main(int argc, char** argv) {
	map<string,bool> foo = dxd((int)(*argv[1]));
	vector<string> entries;
	for (auto const &k : foo) {
		entries.push_back(k.first);
	}
	srand(time(NULL));
	string entry1;
	string entry2;

	LCS lcs;
	float avg = 0.0;

	for (int i = 0; i < (int)(*argv[2]); i++) {
		entry1 = entries[(rand() % entries.size())-1];
		entry2 = entries[(rand() % entries.size())-1];
	    auto s = lcs.Correspondence(entry1, entry2);
	    avg += (entry1.size() - s.size()) / (float)entry1.size();
	}

	avg = avg / (float)(*argv[2]);
	cout << avg << endl;
	//cout << entry1.size() - s.size() << "/" << entry1.size() << endl;

	return 0;
}