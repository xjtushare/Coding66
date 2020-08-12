package basic_class_01;

public class Solution_02_LastRemaining {
        /*
            f(n,m): n个人，第m个人出列，最终剩余的一个人，那个人的序号
            比如f（5，2）=2 ,因为最终剩余的那个学生序号就是2.

            如何推出f(n,m)和F(n01,m)的关系？

             f(n,m)， 下标是从 0 1 2 ... n-1 第一次我要移除的元素下标 (m-1)%n=k 设置为k
            第二次要进行移除的时候，原来的序列其实是分为两段：
            因为当第k个位置移除之后，下一次循环是从第k+1位置开始，所以 k+1,k+2 ... n-1在0到k-1前面。

            k+1,k+2 ... n-1 0... k-1  设置为F(n-1,m)

            说明：
                 F(n-1,m)和f(n,m)的区别就是初始位置不同：0和k+1
                 功能都是去找出n个数字或者n-1个数字当中，第m个位置的下标
                 所以=》 f(n,m)=F(n-1,m) 因为功能一样。

        */
        public static int LastRemainingSolution(int n,int m){
            if(n==0||m==0){
                return -1;
            }
            int ans = 0;//因为是从0开始计数
		/*
		n=1,说明当前只有一个学生，无论m是几，最终返回的都是0；
		所以n从2开始
		*/
            //使用公式： (f(n-1,m)+m)%n = f(n,m)
		/*  f(n,m),从 1 2 3 ..n ,即f(i,m),i从1到n,这里n即n个人
			即 2个人的时候删除第m个位置；3个人的时候删除第m个位置...
		*/
            for (int i=2;i<=n;i++){
                ans = (ans+m)%i;//对应：(f(n-1,m)+m)%n
            }
            return ans;
        }
    }
