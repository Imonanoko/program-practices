#include<iostream>
using namespace std;
int main(void){
    int ary23 [2][3],ary32[3][2];
    cout<<"��J�@��2*3 �M3*2���x�}�ΪŮ���}"<<endl;
    for (int i=0;i<2;i++){
        for(int j=0;j<3;j++){
            cin>>ary23[i][j];
        }
    }
    for (int i=0;i<3;i++){
        for(int j=0;j<2;j++){
            cin>>ary32[i][j];
        }
    }
    int aryout [2][2]={(0,0),(0,0)};
    for (int i=0;i<2;i++){
        for(int j=0;j<3;j++){
            aryout[0][i]+=ary23[0][j]*ary32[j][i];
            aryout[1][i]+=ary23[1][j]*ary32[j][i];
        }

    }
    cout<<"��X�x�}:"<<endl;
    for(int i=0;i<2;i++){
        for(int j=0;j<2;j++){
            cout<<aryout[i][j]<<" ";
        }
        cout<<endl;
    }
}