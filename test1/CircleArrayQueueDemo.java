package test1;


//环形队列的使用

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //队列的空间是4，但是有效数据的个数是3，环形队列中要保留一个空位区分队列空和队列慢两种状态
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
        char key = ' ';             //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        //输出一个菜单
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);                 //接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");

    }
}

//使用数组模拟队列
class CircleArrayQueue{
    private int maxSize;                //数组的最大容量

    //front指向队列的第一个元素，front的初始值为0
    private int front;                  //队列头

    //rear指向队列的最后一个元素的后一个位置，rear的初始值为0
    private int rear;                   //队列尾
    private int[] arr;                  //存放数据，模拟队列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;

    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;

    }

    //判断队列是否空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列已满，不能添加数据");
            return;
        }

        arr[rear] = n;
        //将rear后移，必须取模，否则超出队列的范围
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }

        //front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时变量的值返回

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
        }
        //从front开始遍历
        for (int i = front; i < front + size(); i++){
            System.out.printf("arr[%d] = %d \n" ,i % maxSize ,arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
