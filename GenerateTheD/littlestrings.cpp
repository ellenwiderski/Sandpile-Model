#include <iostream>
#include <string>
#include <math.h>
#include <time.h>
#include <vector>
#include <stdlib.h>
#include <fstream>
using namespace std;

int lcs(string a, string b) {
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
	//cout << (a.size() - result) << endl;
	return result;
}

string B(int n) {
	string bn = "0";

	for (int i = 0; i < n; i++) {
		bn = bn + bn + "1" + bn;
	}

	return bn;
}

void splitLCS(string a, string b) {
	string aa = a.substr(0, a.size()/2);
	string ab = a.substr(a.size()/2, a.size()-1);

	string ba = b.substr(0, b.size()/2);
	string bb = b.substr(b.size()/2,b.size()-1);

	int comp1 = lcs(aa,ba);
	int comp2 = lcs(ab,bb);

	cout << "Distance between original strings: " << lcs(a,b) << endl;
	cout << "Distance between first substrings: " << comp1 << endl;
	cout << "Distance between second substrings: " << comp2 << endl;
	cout << "Addition: " << comp1 + comp2 << endl << endl;

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

	string rand1a = bn.substr(rand() % bn.size(), maxLength);
	string rand1b = bn.substr(rand() % bn.size(), maxLength);

	string word1 = cross(rand1a,rand1b);

	string rand2a = bn.substr(rand() % bn.size(), maxLength);
	string rand2b = bn.substr(rand() % bn.size(), maxLength);

	string word2 = cross(rand2a,rand2b);

	pair<string,string> words (word1,word2);

	return words;
}


int main(int argc, char** argv) {
	ofstream myfile;

	int wordLen;
	int numTests;
	srand(time(NULL));

	cout << "Enter a word length:\n";
	cin >> wordLen;

	cout << "Thanks so much!\nNow enter the number of tests:\n";
	cin >> numTests;

	//myfile.open("lengthOf"+to_string(wordLen)+"Tests"+to_string(numTests)+".csv");

	cout << "Wow, what a great number!\n";

	//myfile << "Word Length = ," << wordLen << endl;

	//myfile << "Iteration Number, Word length - subsequence length\n";

	cout << "Calculating distances" << endl;

	for (int i = 0; i < numTests; i++) {
		pair<string,string> words = dxd(wordLen);
		splitLCS(words.first,words.second);
		//int s = lcs(words.first, words.second);
	    //myfile << i+1 << ", " << wordLen - s<< endl;
	    //if (i % 1 == 0) {
		//    cout << (double)(i) * 100.0 / numTests << "%" << endl;
		//}

	}

	//myfile.close();
	return 0;
}
