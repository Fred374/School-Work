#include <iostream>
#include <algorithm>
using namespace std;
void shout(string);
void whisper(string);
int main(){
    string text = "Hi, I am a pointer to another function.";
    void (*pfun)(string) = shout;
    cout << "\nSHOUT -  " ;    
    pfun = shout;
    pfun(text); // equivalent to shout(text);
    cout << "\nwhisper - ";
    pfun = whisper;
    pfun(text); // equivalent to whisper(text);
    return 0;
}
void shout(string my_str) {
    transform(my_str.begin(), my_str.end(), my_str.begin(), ::toupper);
    cout << my_str<<endl; 
}
void whisper(string my_str){
    transform(my_str.begin(), my_str.end(), my_str.begin(), ::tolower);
    cout << my_str<<endl;
}
