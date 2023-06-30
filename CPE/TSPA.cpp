#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <random>
using namespace std;
struct City {
    double x;
    double y;
};
vector<double> SolveEquation(const vector<double>& a ,const vector<double>& b){//a,b{Constant,Xcoefficient,Ycoefficient}
    vector<double> sloveSet(2);
    double a1 = a.at(1), b1 = a.at(2),a2 =b.at(1),b2 =b.at(2),c1 = -a.at(0),c2 =-b.at(0);
    double det = a1*b2-b1*a2;
    sloveSet.at(0) = (b2*c1-b1*c2)/det;
    sloveSet.at(1) = (-a2*c1+a1*c2)/det;
    return sloveSet;
}
vector<double> LeastSquaresMethod(const vector<City>& cities){
    //suppose y=B1+B2x
    vector<double> slove(2);//{B1,B2}
    vector<double> B2PartialDerivative={0,0,0};//Partial Derivative vector
    vector<double> B1PartialDerivative={0,0,0};//Partial Derivative vector
    for(int i=0;i<cities.size();i++){
        double x=cities.at(i).x, y=cities.at(i).y ;
        B1PartialDerivative.at(0) += -2*y;
        B1PartialDerivative.at(1) += 2;
        B1PartialDerivative.at(2) += 2*x;
        B2PartialDerivative.at(0) += -2*x*y;
        B2PartialDerivative.at(1) += 2*x;
        B2PartialDerivative.at(2) += 2*x*x;
    }
    slove=SolveEquation(B1PartialDerivative,B2PartialDerivative);
    return slove;
}
// 计算两个城市之间的欧几里德距离
double distance(const City& city1, const City& city2) {
    double dx = city1.x - city2.x;
    double dy = city1.y - city2.y;
    return sqrt(dx * dx + dy * dy);
}

// 计算路径的总长度
double calculateTotalDistance(const vector<City>& path) {
    double totalDistance = 0.0;
    for (size_t i = 0; i < path.size() - 1; ++i) {
        totalDistance += distance(path[i], path[i + 1]);
    }
    return totalDistance;
}

// 生成初始解（随机排序的城市序列）
vector<City> generateInitialSolution(const vector<City>& cities) {
    vector<City> solution = cities;
    random_device rd;
    mt19937 generator(rd());
    shuffle(solution.begin(), solution.end(), generator);
    return solution;
}
vector<City> sloveTSP(const vector<City>& cities,int iterations){
    vector<City> currentSolution = generateInitialSolution(cities);
    vector<City> bestSolution = currentSolution;
    double currentDistance = calculateTotalDistance(currentSolution);
    double bestDistance = currentDistance;
    
    random_device rd;
    mt19937 generator(rd());
    uniform_real_distribution<double> distribution(0.0, 1.0);

    for (int i = 0; i < iterations; ++i) {

        // 随机选择两个城市进行交换
        int cityIndex1 = distribution(generator) * (cities.size() - 1);
        int cityIndex2 = distribution(generator) * (cities.size() - 1);
        swap(currentSolution[cityIndex1], currentSolution[cityIndex2]);

        double newDistance = calculateTotalDistance(currentSolution);
        double deltaDistance = newDistance - currentDistance;

        // 根据Metropolis准则决定是否接受新解
        if (deltaDistance < 0 || false) {
            currentDistance = newDistance;

            // 更新最优解
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                bestSolution = currentSolution;
                cout<<i+1<<": "<<calculateTotalDistance(bestSolution)<<endl;
            }
        } else {
            // 恢复之前的解
            swap(currentSolution[cityIndex1], currentSolution[cityIndex2]);
        }
        
    }

    return bestSolution;
}
int main(void){
    vector<City> cities = {
    {88, 16},
    {42, 76},
    {5, 76},
    {69, 13},
    {73,56},
    {100,100},
    {22,92},
    {48,74},
    {73,46},
    {39,1},
    {51,75},
    {92,2},
    {101,44},
    {55,26},
    {71,27},
    {42,81},
    {51,91},
    {89,54},
    {33,18},
    {40,78}
    };
}