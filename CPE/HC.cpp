#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1e9; // 无穷大值

int tsp(int n, vector<vector<int>>& distance, int start, vector<int>& path, vector<bool>& visited, int currentLength, int minLength) {
    if (path.size() == n) {
        currentLength += distance[path.back()][start]; // 回到起始城市的距离
        return min(currentLength, minLength);
    }

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            int nextCity = i;
            int newLength = currentLength + distance[path.back()][nextCity];

            if (newLength < minLength) {
                visited[nextCity] = true;
                path.push_back(nextCity);
                minLength = tsp(n, distance, start, path, visited, newLength, minLength);
                path.pop_back();
                visited[nextCity] = false;
            }
        }
    }

    return minLength;
}

int main() {
    int n;
    cout << "Enter the number of cities: ";
    cin >> n;

    vector<vector<int>> distance(n, vector<int>(n));
    cout << "Enter the distance matrix:" << endl;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> distance[i][j];
        }
    }

    int startCity = 0;
    vector<int> path;
    vector<bool> visited(n, false);
    path.push_back(startCity);
    visited[startCity] = true;

    int minLength = INF;
    minLength = tsp(n, distance, startCity, path, visited, 0, minLength);

    cout << "Minimum tour length: " << minLength << endl;

    return 0;
}
