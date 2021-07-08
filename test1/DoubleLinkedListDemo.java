package test1;

//双向链表的增删改

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1,"唐僧","御弟");
        HeroNode2 hero2 = new HeroNode2(2,"孙悟空","行者");
        HeroNode2 hero3 = new HeroNode2(3,"猪悟能","八戒");
        HeroNode2 hero4 = new HeroNode2(4,"沙悟净","沙僧");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("添加");
        doubleLinkedList.list();
        System.out.println("********************");

        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();
        doubleLinkedList2.addByOrder(hero1);
        doubleLinkedList2.addByOrder(hero3);
        doubleLinkedList2.addByOrder(hero2);
        doubleLinkedList2.addByOrder(hero4);

        System.out.println("顺序添加");
        doubleLinkedList2.list();
        System.out.println("********************");

        HeroNode2 newHeroNode = new HeroNode2(2,"白龙马","小白龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改节点");
        doubleLinkedList.list();

        System.out.println("***************");
        //删除节点
        System.out.println("删除节点");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }
}


class DoubleLinkedList{
    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //添加一个节点添加到链表中
    public void add(HeroNode2 heroNode){

        //head节点不能动，需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环是，temp指向链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;

    }


    //方法二：按序号插入
    public void addByOrder(HeroNode2 heroNode){

        //head节点不能动，需要一个辅助变量temp来找到要插入的位置
        //单链表，temp是插入位置的前一个节点
        HeroNode2 temp = head;
        boolean flag = false;       //标志添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){ //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){    //位置在temp的后面插入
                break;
            }else if (temp.next.no == heroNode.no){ //说明添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;   //后移，遍历当前链表
        }
        //判断flag的值
        if (flag){      //不能添加，说明编号存在
            System.out.printf("插入的编号 %d 已经存在，不能加入链表\n",heroNode.no);
        }else {
            //插入到链表中，temp的后面
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据no编号来修改
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;   //表示是否找到该节点
        while (true){
            if (temp == null){
                break;      //遍历完链表
            }
            if (temp.no == heroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else { //没有找到节点
            System.out.printf("没有找到编号 %d 的节点\n", heroNode.no);
        }

    }

    //删除节点,自我删除
    public void delete(int no){

        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }


        HeroNode2 temp = head.next;
        boolean flag = false;   //标识是否找到待删除节点
        while (true){
            if (temp == null){ //已经找到链表的最后节点的next
                break;
            }
            if (temp.no == no){
                //找到的待删除的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;   //temp后移，遍历
        }
        //判断flag
        if (flag){//找到
            //可以删除
            temp.pre.next = temp.next;
            //如果temp是最后一个节点，则不需要执行下一行，否则空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("没有找到要删除的节点");
        }

    }

    //遍历链表
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.printf("链表为空");
            return;
        }
        //头节点不能动，需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);

            //将temp后移
            temp = temp.next;
        }


    }
}

//定义节点
class HeroNode2{
    public int no;
    public String  name;
    public String nickname;
    public HeroNode2 next;       //指向下一个节点,默认为null
    public HeroNode2 pre;       //指向前一个节点,默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2:[no=" + no + ",name=" + name + ",nickname=" + nickname + "]";
    }


}