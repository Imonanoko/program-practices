#include <iostream>
using namespace std;
int main(void)
{
    int space, gen = 1;
    cout << "��J�Ŷ�(NXN|N:1<=N<=100): ";
    cin >> space;
    bool ary1[100][100] = {0}, ary2[100][100] = {0}, cell, det = 1;
    cout << "��J�s���ӭM����l��J0 0������J: ";
    int a, b;
    while (cin >> a >> b && a != 0 && b != 0)
    {
        ary1[a][b] = 1;
    }
    cout << "��l�ӭM��l" << endl;
    for (int i = 1; i <= space; i++)
    {
        for (int j = 1; j <= space; j++)
        {
            cout << ary1[i][j] << " ";
        }
        cout << endl;
    }
    string str="y";
    while (str == "y" || str == "Y")
    {
        if (det)
        {
            for (int i = 1; i <= space; i++)
            {
                for (int j = 1; j <= space; j++)
                {
                    int total = 0;
                    cell = ary1[i][j];
                    if (cell)
                    {
                        if (i - 1 > 0)
                        {
                            total += ary1[i - 1][j];
                        }
                        if (i - 1 > 0 && j - 1 > 0)
                        {
                            total += ary1[i - 1][j - 1];
                        }
                        if (j - 1 > 0)
                        {
                            total += ary1[i][j - 1];
                        }
                        if (i + 1 <= space && j + 1 <= space)
                        {
                            total += ary1[i + 1][j + 1];
                        }
                        if (i + 1 <= space)
                        {
                            total += ary1[i + 1][j];
                        }
                        if (j + 1 <= space)
                        {
                            total += ary1[i][j + 1];
                        }
                        if (i - 1 > 0 && j + 1 <= space)
                        {
                            total += ary1[i - 1][j + 1];
                        }
                        if (i + 1 <= space && j - 1 > 0)
                        {
                            total += ary1[i + 1][j - 1];
                        }
                        if (total == 2 || total == 3)
                        {
                            ary2[i][j] = 1;
                        }
                        else
                        {
                            ary2[i][j] = 0;
                        }
                    }
                    else
                    {
                        if (i - 1 > 0)
                        {
                            total += ary1[i - 1][j];
                        }
                        if (i - 1 > 0 && j - 1 > 0)
                        {
                            total += ary1[i - 1][j - 1];
                        }
                        if (j - 1 > 0)
                        {
                            total += ary1[i][j - 1];
                        }
                        if (i + 1 <= space && j + 1 <= space)
                        {
                            total += ary1[i + 1][j + 1];
                        }
                        if (i + 1 <= space)
                        {
                            total += ary1[i + 1][j];
                        }
                        if (j + 1 <= space)
                        {
                            total += ary1[i][j + 1];
                        }
                        if (i - 1 > 0 && j + 1 <= space)
                        {
                            total += ary1[i - 1][j + 1];
                        }
                        if (i + 1 <= space && j - 1 > 0)
                        {
                            total += ary1[i + 1][j - 1];
                        }
                        if (total == 3)
                        {
                            ary2[i][j] = 1;
                        }
                        else
                        {
                            ary2[i][j] = 0;
                        }
                    }
                }
            }
            cout << "��" << gen++ << "�N�O: " << endl;
            for (int i = 1; i <= space; i++)
            {
                for (int j = 1; j <= space; j++)
                {
                    cout << ary2[i][j] << " ";
                }
                cout << endl;
            }
            det = 0;
        }
        else
        {
            for (int i = 1; i <= space; i++)
            {
                for (int j = 1; j <= space; j++)
                {
                    int total = 0;
                    cell = ary2[i][j];
                    if (cell)
                    {
                        if (i - 1 > 0)
                        {
                            total += ary2[i - 1][j];
                        }
                        if (i - 1 > 0 && j - 1 > 0)
                        {
                            total += ary2[i - 1][j - 1];
                        }
                        if (j - 1 > 0)
                        {
                            total += ary2[i][j - 1];
                        }
                        if (i + 1 <= space && j + 1 <= space)
                        {
                            total += ary2[i + 1][j + 1];
                        }
                        if (i + 1 <= space)
                        {
                            total += ary2[i + 1][j];
                        }
                        if (j + 1 <= space)
                        {
                            total += ary2[i][j + 1];
                        }
                        if (i - 1 > 0 && j + 1 <= space)
                        {
                            total += ary2[i - 1][j + 1];
                        }
                        if (i + 1 <= space && j - 1 > 0)
                        {
                            total += ary2[i + 1][j - 1];
                        }
                        if (total == 2 || total == 3)
                        {
                            ary1[i][j] = 1;
                        }
                        else
                        {
                            ary1[i][j] = 0;
                        }
                    }
                    else
                    {
                        if (i - 1 > 0)
                        {
                            total += ary2[i - 1][j];
                        }
                        if (i - 1 > 0 && j - 1 > 0)
                        {
                            total += ary2[i - 1][j - 1];
                        }
                        if (j - 1 > 0)
                        {
                            total += ary2[i][j - 1];
                        }
                        if (i + 1 <= space && j + 1 <= space)
                        {
                            total += ary2[i + 1][j + 1];
                        }
                        if (i + 1 <= space)
                        {
                            total += ary2[i + 1][j];
                        }
                        if (j + 1 <= space)
                        {
                            total += ary2[i][j + 1];
                        }
                        if (i - 1 > 0 && j + 1 <= space)
                        {
                            total += ary2[i - 1][j + 1];
                        }
                        if (i + 1 <= space && j - 1 > 0)
                        {
                            total += ary2[i + 1][j - 1];
                        }
                        if (total == 3)
                        {
                            ary1[i][j] = 1;
                        }
                        else
                        {
                            ary1[i][j] = 0;
                        }
                    }
                }
            }
            cout << "��" << gen++ << "�N�O: " << endl;
            for (int i = 1; i <= space; i++)
            {
                for (int j = 1; j <= space; j++)
                {
                    cout << ary1[i][j] << " ";
                }
                cout << endl;
            }
            det = 1;
        }
        cout << "�O�_�~��(Y/N):";
        cin >> str;
        if(str=="n"||str=="N")cout<<"����"<<endl;
    }
}