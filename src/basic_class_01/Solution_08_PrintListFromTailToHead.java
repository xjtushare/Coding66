package basic_class_01;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 * 方法二：递归的方式
 *  模拟链表反置效果
 */
public class Solution_08_PrintListFromTailToHead {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) {//如果传入的是一个空的list，直接返回list
            return list;
        }
        return solve(list, listNode);
    }


    /**
     * solve函数返回值是一个ArrayList, 所以需要把当前层的list作为一个接收值去接收
     * 下一层的返回回来的list，即list = solve(list, listNode.next);
     *
     * @param list
     * @param listNode
     * @return ArrayList
     */
    // 1->2->3->4
    /*即listNode指向4的时候，listNode.next == null，这时候就不再往下递归了
      这个时候执行add操作： list.add(listNode.val);
      即将当前这个节点的值添加到list里面，然后再把这个list返回，返回到哪，就是上一层，
      这里的3这里。，然后把3的值添加到list，返回2；同理。。。
    * */
    private static ArrayList<Integer> solve(ArrayList<Integer> list, ListNode listNode) {
        if (listNode.next != null) {   // 当前节点的下一个节点不为空

            list = solve(list, listNode.next); // 往下递归
        }
        list.add(listNode.val);
//        System.out.println(list);//通过打印list可以看到递归执行的过程
        return list;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(0);//创建一个链表，初始化为0
        ListNode removeNode = head;

        for (int i = 1; i <10 ; i++) {
            ListNode x  = new ListNode(i);//创建ListNode节点对象
            x.next =  null;//初始化当前节点的下一个节点为空值；如果不初始化，next是一个空引用
            removeNode.next =x;//当前链表的尾部就是x
            removeNode = x;//更新removeNode
        }
        System.out.println(printListFromTailToHead(head));//把头节点传进去
    }
}


