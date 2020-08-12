package basic_class_01;

import com.sun.tools.classfile.ConstantPool;

import java.util.ArrayList;

/*
 * 题目：
 *  输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径
 *
 * 考察点：
 *   二叉树的递归遍历，遍历过程中边遍历，边统计路径上的值
 *   并且这个路径指：从根节点开始，到叶子节点结束形成一个的一条路线才是一条路径。
 *   （如果没到叶子节点，不能称为一条路径）
 *
 *  注意：(在返回值的list中，数组长度大的数组靠前 ) --即数组长度越大，在list里面是越靠前的
 *   ArrayList<ArrayList<Integer>> 返回的相当于是一个二维数组
 *   二维数组中，第一个下标代表路径的下标；
 *   第二个下标代表每一条路径中，每一个节点的值
 *
 *  这道题其实：
 *   就是对一个树的DFS，深搜，在深搜遍历过程中，去判断当前所走的路径，它的权值之和，用一个变量记录下来。
 * 当我走到叶子节点的时候，我去判断当前记录的这个权值之和是否等于target。
 * 如果等于，就将当前路径记录下来。
 * 如果不等于，再进行回溯。
 *
 *
 * 在返回值的list中，数组长度大的数组靠前：
 *      当我们找到所有的路径之后，这个路径的长度其实有长有短，
 * 我们其实是类似先序遍历进行搜索的，所以搜索过程中，并不保证所有路径都是按照长度递减顺序保存在
 * ans数组里的，所以最终需要对ans进行排序。这里采用选择排序
 *
 * */
public class Solution_05_FindPath {
    /*
     * 一般将最终返回结果定义成类的成员变量，当成全局变量去处理
     * */
    private ArrayList<ArrayList<Integer>> ans;//类的成员变量，最终要返回的二维数组

    /**
     * @param node   二叉树节点
     * @param target 目标权值和
     * @param sum    当前路径的权值和
     * @param list   保存当前路径
     *               功能：
     *               找到所有权值和等于target的路径
     */
    private void solve(TreeNode node, int target, int sum, ArrayList<Integer> list) {
        //遍历过程先判断
        if (node != null) {//不为空，就把当前节点加入到当前路径当中，同时把当前节点加入list中
            sum += node.val;
            list.add(node.val);
            if (node.left == null && node.right == null) {//说明当前node节点是叶子节点，就去判断sum
                if (sum == target) {//说明我当前已经找到了一条路径是满足sum==target的，此时
                    /*
                     * 相当于把list拷贝了一份，重新创建了一个空间，因为java里面对于map，ArrayList之类的，
                     * 传的是引用，如果对list进行修改的话，也是会修改它内存里面的值的，所以我们需要再去重新
                     * 开辟一个空间，去保存当前的值。
                     * */
                    //ArrayList是引用传递，所以需要通过res去重新开辟空间，去保存当前路径，再将当前路径添加到ans
                    ArrayList<Integer> res = new ArrayList<>(list);//相当于把list拷贝了一份
                    ans.add(res);//将当前路径添加到ans
                } else {//如果if条件不满足,就先对左子树进行遍历，再对右子树进行遍历
                    solve(node.left, target, sum, list);//递归左子树
                    solve(node.right, target, sum, list);//递归右子树
                }
//              当都递归完之后，需要从当前节点回溯到上一层(这时候需要把当前节点产生的影响消除)
                //消除掉当前节点对查找路径的影响
//                sum -= node.val;
                /*
                 * sum是可以不用减的，因为传入sum的时候，传入的是一个int类型的值，
                 * 对于每一层递归，sum都是不一样的，所以sum -= node.val;意义不大
                 * 但是对于list，是需要将最后一个节点元素移出去的，
                 * 因为最后一个节点元素其实就是当前node节点的值，
                 * 当回溯到上一层的时候，需要将当前node节点的值从路径中删除掉。
                 * 其实就相当于把这个节点清除掉
                 * */
                list.remove(list.size() - 1);//至关重要
            }
        }
    }

    private void change() {
        for (int i = 0; i < ans.size(); i++) {//对每一个位置的遍历
            int index = i;
            for (int j = i + 1; j < ans.size(); j++) {//找到对应位置的节点元素
                if (ans.get(j).size() > ans.get(index).size()) {
                    index = j;
                }
            }
            //如果找到了，就交换下
            if (i != index) {
                ArrayList<Integer> temp = ans.get(i);
                ans.set(i, ans.get(index));
                ans.set(index, temp);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ans = new ArrayList<ArrayList<Integer>>();//对ans进行初始化
        ArrayList<Integer> list = new ArrayList<>();
        solve(root, target, 0, list);
        change();
        return ans;
    }
}
