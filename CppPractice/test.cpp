#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <ctime>
using namespace std;
#define SIZE 10

int main()
{
    int ary[SIZE];
    int i, j;
    int temp;

    // 設定亂數種子
    srand(unsigned(time(NULL)));
    for (i = 0; i < SIZE; i++)
        ary[i] = rand() % 100 + 1;

    cout << "Original Array ... " << endl;
    for (i = 0; i < SIZE; i++)
    {
        cout << setw(4) << ary[i];
    }
    cout << endl
         << endl;

    // Insertion Sorting
    cout << "sorting..." << endl;
    for (i = 1; i < SIZE; i++)
    {
        temp = ary[i];
        for (j = i;; j--)
        {
            int x = j - 1;
            if (ary[x] >= temp)
            {
                ary[j] = ary[x];
            }
            else{
                break;
            }
        }
        ary[j] = temp;

        cout << "#" << i << " step: ";
        for (int k = 0; k <= i; k++)
        {
            cout << setw(4) << ary[k];
        }
        cout << endl;
    }

    cout << "\nSorted Array ..." << endl;
    for (i = 0; i < SIZE; i++)
    {
        cout << setw(4) << ary[i];
    }
    cout << endl;

    return 0;
}