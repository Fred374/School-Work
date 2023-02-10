#include <iostream>
using namespace std;
void swapByRef(int&, int&);
void swapByVal(int, int);
int main(){
    int a = 1, b = 2,  c = 100;
    cout << "Before swapping a = " << a << " b = " << b << endl;
    swapByVal(a, b);
    cout << "\nAfter swapByVal a = " << a <<" b = " << b << endl;
    swapByRef(a, b);
    cout << "\nAfter swapByRef a = " << a << " b = " << b <<endl;    
    return 0;
}
void swapByRef(int& n1, int& n2) {
    int temp = n1;     n1 = n2;
    n2 = temp;  
}
void swapByVal(int n1, int n2){
    int temp = n1;     n1 = n2;
    n2 = temp;
}
