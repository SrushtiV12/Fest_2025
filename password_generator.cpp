#include <iostream>
#include <string>
#include <vector>
#include <random>
#include <algorithm>
#include <cctype>
#include <limits>

using namespace std;

const string LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
const string UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const string NUMBERS = "0123456789";
const string SYMBOLS = "!@#$%^&*()-_=+[]{}|;:',.<>/?";

char getRandomChar(const string& pool, mt19937& gen) {
    uniform_int_distribution<> dis(0, pool.length() - 1);
    return pool[dis(gen)];
}

bool getYesNo(const string& prompt) {
    char response;
    while (true) {
        cout << prompt << " (y/n): ";
        cin >> response;
        response = tolower(response);

        if (response == 'y') {
            return true;
        } else if (response == 'n') {
            return false;
        } else {
            cout << "Invalid input. Please enter 'y' or 'n'.\n";
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
}

string generatePassword(int length, bool useLower, bool useUpper, bool useNums, bool useSyms, mt19937& gen) {
    
    string charPool = "";
    vector<char> passwordChars;

    if (useLower) {
        charPool += LOWERCASE;
        passwordChars.push_back(getRandomChar(LOWERCASE, gen));
    }
    if (useUpper) {
        charPool += UPPERCASE;
        passwordChars.push_back(getRandomChar(UPPERCASE, gen));
    }
    if (useNums) {
        charPool += NUMBERS;
        passwordChars.push_back(getRandomChar(NUMBERS, gen));
    }
    if (useSyms) {
        charPool += SYMBOLS;
        passwordChars.push_back(getRandomChar(SYMBOLS, gen));
    }

    if (charPool.empty()) {
        return "Error: No character sets selected.";
    }

    int remainingLength = length - passwordChars.size();
    if (remainingLength < 0) {
        remainingLength = 0; // Handle case where length is less than guaranteed chars
    }

    for (int i = 0; i < remainingLength; ++i) {
        passwordChars.push_back(getRandomChar(charPool, gen));
    }
    
    // Ensure final password is exactly the requested length
    while (passwordChars.size() > length) {
        passwordChars.pop_back();
    }

    shuffle(passwordChars.begin(), passwordChars.end(), gen);

    return string(passwordChars.begin(), passwordChars.end());
}


int main() {
    random_device rd;
    mt19937 gen(rd());

    cout << "Password Generator\n";

    while (true) {
        int length = 0;
        int minLength = 0; // Will be set by selected options

        while (true) {
            cout << "Enter password length: ";
            if (!(cin >> length)) {
                cout << "Invalid input. Please enter a number.\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            } else {
                break; // Will validate length after getting options
            }
        }
        
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        bool useLower = getYesNo("Include lowercase?");
        bool useUpper = getYesNo("Include uppercase?");
        bool useNums = getYesNo("Include numbers?");
        bool useSyms = getYesNo("Include symbols?");
        
        if (useLower) minLength++;
        if (useUpper) minLength++;
        if (useNums) minLength++;
        if (useSyms) minLength++;

        if (minLength == 0) {
            cout << "\nError: You must select at least one character set.\n";
            cout << "Using default: lowercase + numbers.\n";
            useLower = true;
            useNums = true;
            minLength = 2;
        }

        // Now validate length against the minimum required
        while (length < minLength) {
             cout << "Length must be at least " << minLength << " to include all selected types.\n";
             cout << "Enter password length: ";
             while (!(cin >> length)) {
                cout << "Invalid input. Please enter a number.\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
             }
             cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }

        string password = generatePassword(length, useLower, useUpper, useNums, useSyms, gen);
        cout << "\nYour generated password is:" << password;
        cout << "\n";

        if (!getYesNo("\nGenerate another password?")) {
            break;
        }
        cout << "\n";
    }

    cout << "Goodbye!\n";
    return 0;
}