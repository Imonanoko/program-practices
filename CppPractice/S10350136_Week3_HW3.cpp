#include<iostream>
using namespace std;
int main(void){
    int years,mod4,mod100,mod400;
    cout<<"��J�~��:";
    cin>>years;
    mod4=years%4;
    mod100=years%100;
    mod400=years%400;
    if (mod100!=0 && mod4==0){
        cout<<"�|�~";
    }
    else if (mod100==0 && mod400==0){
        cout<<"�|�~";
    }
    else{cout<<"���~";}
} 