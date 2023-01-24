#include <iostream>
#include <ctime>
using namespace std;
void swapByRef(int&, int&);
struct BigArray {
    int elements[10000] ;
};
void swapByVal(struct BigArray, struct BigArray);

int main(){
    int a[10000], b[10000];
    struct BigArray n1, n2;
    for( int i = 0; i < 10000; i = i + 1 ){
        a[i] = i; b[i] = i;
        n1.elements[i] = i;
        n2.elements[i] = i;
    } 
    clock_t begin = clock();
    swapByRef(*a, *b);
    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    cout<<"SwapByRef time spent: "<<time_spent<<endl;
    clock_t begin2 = clock();
    swapByVal(n1, n2);
    clock_t end2 = clock();
    double time_spent2 = (double)(end2 - begin2) / CLOCKS_PER_SEC;
    cout<<"SwapByVal time spent: "<<time_spent2<<endl; 
    return 0;
}
void swapByRef(int& n1, int& n2) {   }
void swapByVal(struct BigArray n1, struct BigArray n2){  }
