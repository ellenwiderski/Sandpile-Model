#include <iostream>
#include <string>
#include <math.h>
#include <time.h>
#include <vector>
#include <stdlib.h>
#include <fstream>
#include <assert.h>
using namespace std;

<<<<<<< HEAD
int kirbylcs(string a, string b) {
=======
int lcs(string a, string b) {
	cout << a.size() << "x" << b.size() << "___";
>>>>>>> b19bb3498b6b5e8e461e74b5665c5d7895705886
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
<<<<<<< HEAD
	//cout << (a.size() - result) << endl;
	return result;
}

int lcs(string a, string b) {
	vector<int> inside (b.size()+1, 0);
	vector<vector<int>> lengths (a.size()+1, inside);

	for (int i = 0; i < a.size(); i++) {
		for (int j = 0; j < b.size(); j++) {
			if (a[i] == b[j]) {
				lengths[i+1][j+1] = lengths[i][j] + 1;
			}
			else {
				lengths[i+1][j+1] = max(lengths[i+1][j],lengths[i][j+1]);
			}
		}
	}

	int result = 0;

	int x = a.size();
	int y = b.size();

	while (x != 0 && y != 0) {
		if (lengths[x][y] == lengths[x-1][y]) {
			x--;
		}
		else if (lengths[x][y] == lengths[x][y-1]) {
			y--;
		}
		else {
			assert(a[x-1] == b[y-1]);
			result++;
			x--;
			y--;
		}
	}
=======
>>>>>>> b19bb3498b6b5e8e461e74b5665c5d7895705886
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

<<<<<<< HEAD
	string rand1a = bn.substr(rand() % (bn.size()-maxLength+1), maxLength);
	string rand1b = bn.substr(rand() % (bn.size()-maxLength+1), maxLength);

	string word1 = cross(rand1a,rand1b);

	string rand2a = bn.substr(rand() % (bn.size()-maxLength+1), maxLength);
	string rand2b = bn.substr(rand() % (bn.size()-maxLength+1), maxLength);
=======
	string rand1a = bn.substr(rand() % (bn.size() - maxLength), maxLength);
	string rand1b = bn.substr(rand() % (bn.size() - maxLength), maxLength);

	string word1 = cross(rand1a,rand1b);

	string rand2a = bn.substr(rand() % (bn.size() - maxLength), maxLength);
	string rand2b = bn.substr(rand() % (bn.size() - maxLength), maxLength);
>>>>>>> b19bb3498b6b5e8e461e74b5665c5d7895705886

	string word2 = cross(rand2a,rand2b);

	pair<string,string> words (word1,word2);

	return words;
}


int main(int argc, char** argv) {
	ofstream myfile;

	int wordLen = 1000000;
	int numTests = 333;
	srand(time(NULL));

<<<<<<< HEAD
	cout << "Enter a word length:\n";
	cin >> wordLen;

	cout << "Thanks so much!\nNow enter the number of tests:\n";
	cin >> numTests;

=======
>>>>>>> b19bb3498b6b5e8e461e74b5665c5d7895705886
	myfile.open("lengthOf"+to_string(wordLen)+"Tests"+to_string(numTests)+".csv");

	myfile << "Word Length = ," << wordLen << endl;

	myfile << "Iteration Number, Word length - subsequence length\n";

	for (int i = 0; i < numTests; i++) {
		pair<string,string> words = dxd(wordLen);
		int s = lcs(words.first, words.second);
<<<<<<< HEAD
	    myfile << i+1 << ", " << wordLen - s<< endl;
	    if (i % 1 == 0) {
		    cout << (double)(i) * 100.0 / numTests << "%" << endl;
		}

=======
		myfile << i+1 << ", " <<wordLen - s<< endl;
>>>>>>> b19bb3498b6b5e8e461e74b5665c5d7895705886
	}

	myfile.close();
	return 0;
}
