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
        for(int i = 0; i < 1; i++){
            swap(heap,0,last);
            printArray(heap);
            maintainHeapDownwards(heap, 0);
            last--;
        }

        return heap;
    }

    static void maintainHeapDownwards(Integer[] heap, int i){
        int left = ((i+1)*2)-1;
        int right = left + 1;
        System.out.println(i + " " + left + " " + right);

        if(left >= heap.length){
            System.out.println("hit6");
            System.out.println(left);
            return;
        }
        if(right >= heap.length){
            System.out.println("hit7");
            return;
        }
        
        if(heap[left] == null && heap[right] == null){
            System.out.println("double null");
            return;
        }
        else if(heap[left] != null && heap[right] != null && heap[left] > heap[i] && heap[left] >= heap[right]){
            System.out.println("hit");
            swap(heap,left,i);
            maintainHeapDownwards(heap, left);
        }
        else if(heap[left] != null && heap[right] != null && heap[right] > heap[i] && heap[right] > heap[left]){
            System.out.println("hit2");
            swap(heap,right,i);
            printArray(heap);
            maintainHeapDownwards(heap, right);
        }
        else if(heap[left] != null && heap[left]  > heap[i]){
            System.out.println("hit3");
            swap(heap,left,i);
            maintainHeapDownwards(heap, left);
        }

        else if(heap[right] != null && heap[right]  > heap[i]){
            System.out.println("hit4");
            swap(heap,right,i);
            maintainHeapDownwards(heap, right);
        }
        else{
            System.out.println("hit5");
            return;
        }
    }
    public static void main(String[] args) {
        Integer[] array = {10,20,15,30,40,Integer.MAX_VALUE, Integer.MIN_VALUE};
        printArray(heapSort(array));
    }
}
