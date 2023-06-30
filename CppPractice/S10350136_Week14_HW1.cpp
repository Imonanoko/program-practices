#include <iostream>
using namespace std;
#define SIZE 3
template <typename T>
class Stack
{
public:
    Stack();
    void push(T);
    T pop();
    void display();

private:
    T stack[SIZE];
    int pointTo;
};
template <typename T>
Stack<T>::Stack()
{
    pointTo = -1;
}
template <typename T>
void Stack<T>::push(T data)
{
    if (pointTo < SIZE - 1)
    {
        pointTo += 1;
        stack[pointTo] = data;
        cout << "�[�J��Ʃ���|" << data << endl;
    }
    else
    {
        cout << "Stack overflow" << endl;
    }
}
template <typename T>
T Stack<T>::pop()
{
    T temp;
    if (pointTo >= 0)
    {
        temp = stack[pointTo];
        pointTo -= 1;
        cout << "�q���|�R�����" << temp << endl;
        return temp;
    }
    else
    {
        cout << "Stack underflow" << endl;
        return 0;
    }
}
template <typename T>
void Stack<T>::display()
{
    cout << "��ư��|�p�U: ";
    for (int i = pointTo; i >= 0; i--)
    {
        cout << stack[i] << "->";
    }
    cout << "NULL" << endl;
}
int main(void)
{
    cout<<"�п�J�n��J���ާ@"<<"\n1:�[�J"<<"\n2:�R��"<<"\n3:���"<<"\n5:����"<<endl;
    Stack<double> stackObj2;
    int a;
    while(cin>>a){
        switch (a)
        {
        case 1:
            double b;
            cin>>b;
            stackObj2.push(b);
            break;
        case 2:
            stackObj2.pop();
            break;
        case 3:
            stackObj2.display();
            break;
        
        case 5:
            goto end;
            break;
        }
    }
    end:
    cout<<"����";
}
