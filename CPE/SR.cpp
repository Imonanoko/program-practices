#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <random>

using namespace std;

// 表示一个城市的结构体
struct City {
    double x;
    double y;
};

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

// 执行模拟退火算法来解决TSP问题
vector<City> solveTSP(const vector<City>& cities, double initialTemperature, double coolingRate, int iterations) {
    vector<City> currentSolution = generateInitialSolution(cities);
    vector<City> bestSolution = currentSolution;
    double currentDistance = calculateTotalDistance(currentSolution);
    double bestDistance = currentDistance;

    random_device rd;
    mt19937 generator(rd());
    uniform_real_distribution<double> distribution(0.0, 1.0);

    for (int i = 0; i < iterations; ++i) {
        double temperature = initialTemperature * exp(-coolingRate * i);

        // 随机选择两个城市进行交换
        int cityIndex1 = distribution(generator) * (cities.size() - 1);
        int cityIndex2 = distribution(generator) * (cities.size() - 1);
        swap(currentSolution[cityIndex1], currentSolution[cityIndex2]);

        double newDistance = calculateTotalDistance(currentSolution);
        double deltaDistance = newDistance - currentDistance;

        // 根据Metropolis准则决定是否接受新解
        if (deltaDistance < 0 || distribution(generator) < exp(-deltaDistance / temperature)) {
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

int main() {
    // 定义一些城市
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

    // 设置模拟退火算法的参数
    double initialTemperature = 100.0;
    double coolingRate = 0.003;
    int iterations = 10000;

    // 解决TSP问题
    vector<City> bestSolution = solveTSP(cities, initialTemperature, coolingRate, iterations);

    // 输出最优解的路径和总长度
    cout << "Best solution path: ";
    for (const City& city : bestSolution) {
        cout << "(" << city.x << ", " << city.y << ") ";
    }
    cout << endl;
    cout << "Total distance: " << calculateTotalDistance(bestSolution) << endl;

    return 0;
}
