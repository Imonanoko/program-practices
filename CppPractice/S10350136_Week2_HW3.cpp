#include <iostream>
#include <iomanip>
using namespace std; 
int main(void){
    int score1,score2,score3,score4,score5;
    cout<<"��J�A��C++���ɧ@�~�B���ɦҡB�����ҡB�����ҡA�H�ΤW�����ժ����ơA�åH�Ů���}:";
    cin>>score1>>score2>>score3>>score4>>score5;
    float a=0.2,b=0.25,c=0.15;
    float final_score=(score1+score2+score5)*a+score3*b+score4*c;
    cout<<"�AC++�̫᪺���ƬO"<<final_score;
}