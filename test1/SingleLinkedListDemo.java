package test1;

//单链表的增删改
//

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1,"唐僧","御弟");
        HeroNode hero2 = new HeroNode(2,"孙悟空","行者");
        HeroNode hero3 = new HeroNode(3,"猪悟能","八戒");
        HeroNode hero4 = new HeroNode(4,"沙悟净","沙僧");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero1);
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);

        System.out.println("尾插法");
        singleLinkedList.list();
        System.out.println("***************");
        System.out.println("按照序号排序");
        singleLinkedList2.list();

        System.out.println("***************");
        //修改节点
        HeroNode newHeroNode = new HeroNode(2,"白龙马","小白龙");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改节点");
        singleLinkedList.list();

        System.out.println("***************");
        //删除节点
        System.out.println("删除节点");
        singleLinkedList.delete(3);
        singleLinkedList.list();
    }
}

//定义SingleLinkedList
class SingleLinkedList{
    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");

    //方法一：尾插法
    //添加节点到单向链表，按照添加顺序添加
    //1.找到当前链表的最后一个节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){

        //head节点不能动，需要一个辅助变量temp
        HeroNode temp = head;
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
        temp.next = heroNode;
    }


    //方法二：按序号插入
    public void addByOrder(HeroNode heroNode){

        //head节点不能动，需要一个辅助变量temp来找到要插入的位置
        //单链表，temp是插入位置的前一个节点
        HeroNode temp = head;
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
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    //修改节点信息，根据no编号来修改
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，定义一个辅助变量
        HeroNode temp = head.next;
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

    //删除节点
    //1.head不能动，因此我们需要一个temp来找到待删除节点的前一个节点
    //2.需要temp.next.no和需要删除的节点的no比较
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;   //标识是否找到待删除节点
        while (true){
            if (temp.next == null){ //已经找到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到的待删除的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;   //temp后移，遍历
        }
        //判断flag
        if (flag){//找到
            //可以删除
            temp.next = temp.next.next;
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
        HeroNode temp = head.next;
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
class HeroNode{
    public int no;
    public String  name;
    public String nickname;
    public HeroNode next;       //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode:[no=" + no + ",name=" + name + ",nickname=" + nickname + "]";
    }


}