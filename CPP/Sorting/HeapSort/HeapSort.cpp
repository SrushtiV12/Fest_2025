#include<iostream>
using namespace std;
void heapify(int arr[], int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && arr[l] > arr[largest])
        largest = l;


    if (r < n && arr[r] > arr[largest])
        largest = r;


    if (largest != i) {
        swap(arr[i], arr[largest]);

        
        heapify(arr, n, largest);
    }
}

void heapSort(int arr[], int n) {
    
    for (int i = (n / 2) - 1; i >= 0; i--)
        heapify(arr, n, i);

    
    for (int i = n - 1; i >= 0; i--) {
        
        swap(arr[0], arr[i]);

        
        heapify(arr, i, 0);
    }
}


void Print(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        cout << arr[i] << "\t";
    }
    cout << "\n";
}

int main() {
    
    int arr[] = {2,1,12,14,8};
    int n = sizeof(arr) / sizeof(arr[0]);
    cout << "Without Sorting  \n";
    Print(arr, n);

    heapSort(arr, n);

    cout << "After Sorting the array looks as :  \n";
    Print(arr, n);
}