#include <iostream>
using namespace std;
bool prime(int);
string factor(int);
int main(void){
    int a;
    cout<<"�п�J�@�Ӿ��:";
    cin>>a;
    if (prime(a)){cout<<a<<"�O���";}
    else{
        cout<<a<<"���]�ƬO"<<factor(a);
    }
}
bool prime(int x){
    for (int i=2;i<(x/2)+1;i++){
        if(x%i!=0){
            continue;
        }
        else{
            return false;
        }
    }
    return true;
}
string factor(int a){
    int b=2;
    string c;
    while(a>=b){
        int time=0;
        while(a%b==0){
            a=a/b;
            time++;
        }
        if(time==0){
            b++;
            continue;}
        else{
            string e(to_string(time));
            string d(to_string(b));
            c+=d+"^"+e+"*";
        }
        b++;
    }
    c=c.erase(c.length()-1);
    return c;
}