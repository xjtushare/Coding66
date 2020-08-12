package basic_class_01;

import basic_class_01.ListNode;

public class Solution_03_ReverseList {
    /*
     输入一个链表，反转链表后，输出新链表的表头

     思路：(3种方法：递归、栈、双指针)
     方法三：设置两个移动节点，距离为1
         让链表上原来相邻的两个节点的指向给逆过来。

         关键点：怎么对两个两个移动节点进行操作，达到两个节点的指向逆过来
    */
    public ListNode ReverseList(ListNode head) {
        //首先判断，如果head为空，就没必要进行反转了
        if (head == null) {
            return null;
        }
        //如果链表不为空，首先设置两个移动节点
        ListNode frontNode = head;//移动节点1
        ListNode removeNode = head.next;//移动节点2
        //操作两个移动节点，进行翻转
        while (removeNode != null) {
			/*对于第一次翻转，有个坑，就是将移动节点2所指向的节点的next指向head
			所以说如果直接将移动节点2的next指向移动节点1的话，那么在下一次遍历的时候
			就找不到节点，就相当于这条路断了。相当于把动节点2和后面节点分离开了
			所以在移动节点2所指向的节点的next指向head(移动节点1指向的节点)时候，
			还是需要一个ListNode类型的变量去保存当前移动节点2原来所指向的下一个节点
			*/
            ListNode tempNode = removeNode.next;//用来保存移动节点的下一个节点，不然会造成节点最终无法往右移情况

            //实现节点反置
            removeNode.next = frontNode;//相当于把移动节点2的next给指向前一个
            //这时候相当于翻转操作已经结束了，然后需要把两个节点更新，即同时往右移动1位

            //实现两个节点的向右平移
            frontNode = removeNode;//即移动节点1到了移动节点2的位置
            removeNode = tempNode; //移动节点2就等于原来移动节点2的下一个位置

        }
        //原始链表的head没有删除，如果不删除，会出现死循环的情况
        head.next = null;
        return frontNode;//最终返回frontNode
    }
}