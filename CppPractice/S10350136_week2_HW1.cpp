#include <iostream>
#include <string>
#include <iomanip>
using namespace std;
int main(void){
    string A, B, C;
    float a,b,c;
    cout<<"�п�J�Ĥ@�Ӭ�ئW�٩M���Z";
    cin>>A>>a;
    cout<<"�п�J�ĤG�Ӭ�ئW�٩M���Z:";
    cin>>B>>b;
    cout<<"�п�J�ĤT�Ӭ�ئW�٩M���Z:";
    cin>>C>>c;
    cout<<fixed<<setprecision(1);
    cout<<left<<setw(15)<<A<<right<<setw(5)<<a<<endl;
    cout<<left<<setw(15)<<B<<right<<setw(5)<<b<<endl;
    cout<<left<<setw(15)<<C<<right<<setw(5)<<c<<endl;
}