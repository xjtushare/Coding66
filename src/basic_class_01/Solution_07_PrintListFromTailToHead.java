package basic_class_01;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 * <p>
 * 方法一：使用栈
 * 思路：
 * 如果边遍历边保存，得到的是从头到尾的顺序。
 * 这样的反置的效果，可以通过栈或递归的方式来实现
 * <p>
 * 实现：
 * 定义一个栈，
 * 遍历链表，传入了listnode;当链表不空，将当前节点值放入到栈，并更新listnode值，要不然陷入死循环
 * <p>
 * 判断栈是否为空，如果不空，则把栈顶元素取出来，放入list里面
 * 直到栈空了，说明栈中元素都放入list里去了。
 */
public class Solution_07_PrintListFromTailToHead {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null) {
            stack.add(listNode.val);//取当前节点的值放入栈中
            listNode = listNode.next;//更新当前节点为下一个节点
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());//取出当前栈顶元素，然后放入list中
        }
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


