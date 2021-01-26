//Use this class to implement turning an unsorted array into a heap and performing heap sort
public class Heap {
    static void swap(Integer[] array, int firstIdx, int secondIdx){
        int temp = array[firstIdx];
        array[firstIdx] = array[secondIdx];
        array[secondIdx] = temp;
        return;
    }

    static Integer[] heapify(Integer[] array){
        Integer[] heap = new Integer[array.length];
        initNullArray(heap);
        for(int i = 0; i < array.length; i++){
            heap[i] = array[i];
            maintainHeapUpwards(heap, i);
        }
        return heap;
    }

    static void maintainHeapUpwards(Integer[] heap, int i){
        if(i == 0)return;

        int parent = ((i + 1)/2) - 1;
        if(heap[i] > heap[parent]){
            swap(heap,i,parent);
            maintainHeapUpwards(heap, parent);
        }

        return;
    }

    static void printArray(Integer[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]);
            if(i != array.length -1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void initNullArray(Integer[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = null;
        }
    }

    static Integer[] heapSort(Integer[] array){
        Integer[] heap = heapify(array);
        printArray(heap);
        int last  = array.length - 1;
        for(int i = 0; i < heap.length; i++){
            swap(heap,0,last);
            heapDown(heap, last,0);
            last--;
        }

        return heap;
    }

    static void heapDown(Integer[] arr, int length, int index){
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if(left < length && arr[left] > arr[largest]){
            largest = left;
        }

        if(right < length && arr[right] > arr[largest]){
            largest = right;
        }

        if(largest != index){
            swap(arr,largest,index);
            heapDown(arr,length,largest);
        }
    }

    
    public static void main(String[] args) {
        Integer[] array = {10,20,15,30,40,Integer.MAX_VALUE, Integer.MIN_VALUE};
        printArray(heapSort(array));
    }
}
