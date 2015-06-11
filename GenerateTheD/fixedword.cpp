#include <iostream>
#include <string>
#include <math.h>
#include <map>
#include <time.h>
#include <vector>
#include <stdlib.h>
#include "HammingDistance.h"
#include <fstream>
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

void dxd(int maxLength) {

	ofstream dxdfile;
	dxdfile.open("dxd.txt");

	int bnLength = ceil(0.5529610484 + 0.9220896727*log(maxLength));
	string bn = B(bnLength);

	map<string,bool> temp;

	int j = 0;

	cout << "Generating all the D's" << endl;

	while (j < bn.size() - maxLength && temp.size() < 2 * maxLength - 1) {
		string key = bn.substr(j, maxLength);
		temp[key] = false;
		j++;

		if (j % 100 == 0)
			cout << j * 100.0 / (bn.size() - maxLength) << "%" << endl;
	}

	cout << "Making DxD" << endl;

	int it = 0;

	int size = temp.size();
	string word;

	for(pair<string,bool> const &s : temp) {
		for(pair<string,bool> const &t : temp) {
			word = cross(s.first,t.first);
			dxdfile << word << endl;
		}
		cout << it * 100.0 / size << "%" << endl;
		it++;
	}
	dxdfile.close();
}

int main(int argc, char** argv) {
	ofstream myfile;
	ifstream dxdfile;
	myfile.open("out.csv");

	int numTests;
	string fixedWord;

	cout << "Enter a word:\n";
	cin >> fixedWord;

	int wordLen = fixedWord.size();

	cout << "Thanks so much!\nNow enter the number of tests:\n";
	cin >> numTests;

	myfile << "Word is: " << fixedWord << endl;
	myfile << "Iteration Number, Distance\n";

	dxd(wordLen);
	
	LCS lcs;
	srand(time(NULL));
	int rand2;
	string entry2;

	string line;

	cout << "Calculating distances" << endl;

	for (int i = 0; i < numTests; i++) {
		int it = 0;
		dxdfile.open("dxd.txt");
		rand2 = rand() % ((2*wordLen-1)*(2*wordLen-1));

		while (getline(dxdfile,line)) {
			if (it == rand2) {
				entry2 = line;
			}
			it++;
		}
	    string s = lcs.Correspondence(fixedWord, entry2);
	    myfile << i+1 << ", " << (wordLen - s.size()) / (double)(wordLen) << endl;
	    if (i % 5 == 0) {
		    cout << (double)(i) * 100.0 / numTests << "%" << endl;
		}
		dxdfile.close();
	}

	myfile.close();
	return 0;
}