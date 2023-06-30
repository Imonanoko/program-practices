#include <iostream>
#include <chrono>
#include <thread>
using namespace std;
int main()
{
    auto start1 = std::chrono::high_resolution_clock::now();
    for(int i=1;i<10;i++){
        for(int j=1;j<10;j++){
            for(int k = 1; k<10;k++){
                cout<<i<<"X"<<j<<"X"<<k<<"="<<i*j*k<<endl;
            }
        }
    }
    auto end1 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> float_ms1 = end1 - start1;
    cout<<"°Ï¶ô1:"<<float_ms1.count()<<" ms"<<endl;



    auto start2 = std::chrono::high_resolution_clock::now();
    



    
    auto end2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> float_ms2 = end2 - start2;
    cout<<"°Ï¶ô2:"<<float_ms2.count()<<" ms"<<endl;
    return 0;
}