package basic_class_01;

import basic_class_01.ListNode;

import java.util.Stack;

public class Solution_04_ReverseList {
    /*
     * 方法二：不推荐，提供参考，使用栈
     * 思路：
     *    通过遍历链表的每一个节点，然后把节点放入到栈里面
     * 当遍历结束后，从栈中取出每一个节点的元素，每取出一个元素，就把它的next指向
     * 下一次要取出的元素即可
     *
     * 但是这种思路本质上还是重新的去创建了一个链表
     * */
    public ListNode ReverseList(ListNode head) {
        //首先判断，如果head为空，就没必要进行反转了
        if (head == null) {
            return null;
        }
        //如果不等于null，就去定义一个栈
        Stack<ListNode> stack = new Stack<>();

        //把所有的节点都放入到栈里面
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        //再开始取出栈中的元素，然后创建新的链表
        ListNode removeNode = stack.pop();//创建新的链表，需要取出头部，创建一个新的引用
        ListNode ans = removeNode;
        removeNode.next = null;//先初始化成空值
        while (!stack.isEmpty()) {
            /*
             * 取出栈顶节点元素，然后初始化节点元素的next值
             * 因为 stack.push(head);在往栈中放入节点元素的时候，
             * 每一个节点的next其实都是有值的，因为现在要去改变原来链表的指向的，
             * 所以在从栈中取的时候，需要将原来节点的next置成null，
             * 然后让removeNode，就是这个遍历节点等于当前节点，
             * 然后再去更新removeNode节点
             * */
            //链表的头部，其实就是栈顶元素
            ListNode x = stack.pop();//取出栈顶节点元素，然后初始化节点元素的next值
            x.next = null;
            //可以用链表的尾接法(链表的创建有尾接法和头接法)去理解
            removeNode.next = x;
            removeNode = x;
        }
        return ans;
    }
}

