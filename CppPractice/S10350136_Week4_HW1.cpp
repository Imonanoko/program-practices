#include<iostream>
using namespace std;
int main(void){
    cout<<"�п�J20�Ӿ��";
    int a,even=0,odd=0;
    for (int i=0;i<20;i++){
        cin>>a;
        if (a%2==0){
            even++;
        }
        else{
            odd++;
        }
    }
    cout<<"���Ʀ�"<<even<<"��"<<endl<<"�_�Ʀ�"<<odd<<"��";
}