## LeetCode319. 灯泡开关

```
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName LeetCode319
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2020/2/17  9:48
 * @Version V1.0
 * 题目描述：
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bulb-switcher
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode319 {

    /**
     * 暴力解法：进行n轮，每一轮整除的数乘以（-1）
     * 复杂度：O(n^2)
     * @param n
     * @return
     */
  public   static    int   bubswitch(int  n ){
      int[]   bubArr=new  int[n];
      Arrays.fill(bubArr,1);

      for (int i = 0; i <n ; i++) {//n 轮

          for (int j = 0; j < n; j++) {//每一轮

              if(i%(j+1)==0){
                 bubArr[j]=bubArr[j]*(-1);
              }
          }
      }
      int  result=0;
      for (int i = 0; i <n ; i++) {
          if(bubArr[i]==1){
              result++;
          }
      }
      return  result;

  }

    /**
     * 解法二找规律
     * 每个灯泡进行了几次状态切换？
     * 灯泡1 ：1  共 1次---- 亮
     * 灯泡2:1,2 总共2 次
     * 灯泡3：1，3 总共2次
     * 灯泡4:1,2,4 总共3 次----亮
     * 灯泡5:1,5  总共2次
     * 灯泡6:1，2,3,6 总共4次
     * 灯泡7: 1,7 总共4  次
     * 灯泡8: 1,2,4,8 总共4 次
     * 灯泡9 ：1,3,9  总共3 次 ---亮
     * 如果一个灯泡的序号为j 当前操作的数为i.
     * 那么j%i==0 的时候该灯会被操作。
     * 比如 6 ： 1,2,3,6  最终会灭掉因数都是成对出现。
     * 完全平方数 9  =3*3 因数3 只能出现一次所以是奇数次
     * 其中1,4,9 是完全平方数问题转换为求[1,n] 之间的完全平方数的个数。
     * 可以通过遍历方式找完全平方数复杂度O(n)
     *
     * 第i个灯泡从最初状态到最后操作完毕，被操作的次数等价于i的约数个数
     * 如果灯最终是亮着的，则说明i有奇数个约数。
     * 问题转化为求那几个数有奇数个约数：
     * 一个数如果有奇数个约数，则这个数肯定是个完全平方数
     *
     * 所以只需要找出【1，n】中包含多少个完全平方数即可，即对n进行开方取整。
     * 
     * 
     * 
     * 什么是完全平方数？
     * 一个数如果可以表示为某个整数的平方形式则称这个数是完全平方数。
     *执行用时 :
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗 :
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 5.38%
     * 的用户
     *
     */

        public  static int bulbSwitch(int n) {
            return  (int)Math.sqrt(n);
        }


   @Test
  public   void    test1(){


       System.out.println(bubswitch(3));
  }

    /**
     * 试题2描述
     *  一个数如果是另一个整数的完全平方，那么我们就称这个数为完全平方数，也叫做平方数。例如：0, 1, 4, 9, 16, 25,……
     *  由键盘输入正整数n，请你编程用循环判断该数是否为完全平方数（不允许使用sqrt函数），是输出“TRUE”，否则输出“FALSE”。请用循环实现。
     *  输入
     *  输入一个正整数n，且1 <= n <= 30000。
     *  输出
     *  根据题意输出“TRUE”或“FALSE”（不输出引号）。
     *  输入示例1
     *  100
     *  输出示例1
     *  TRUE
     *  输入示例2
     *  15
     *  输出示例2
     *  FALSE
     *  数据范围
     *  对于100%的数据，1 <= n <= 30000
     */
    @Test
    public   void   test2(){

        boolean perfectSquare = isPerfectSquare(36);
        System.out.println(isPerfectSquare(36));
        System.out.println(isPerfectSquare(4));
        System.out.println(isPerfectSquare(3000));


    }
   public  static   boolean  isPerfectSquare(int    n){

        if(n<1 ||n> 30000){

            return  false;
        }
       for (int i = 1; i <n ; i++) {
           if(n==i*i){
               return  true;
           }
       }
      return false;
   }
    /**
     * 试题3 ： 求k的最大值，使得2010可以表示为k个连续正整数之和。
     *    a+(a+1) +(a+2)+...+(a+k-1)
     *    a*k + 1+2+3+...+k-1
     *    =a*k+ (1+ (k-1))*(k-1)/2
     *    =ak+(k(k-1))/2=2010
     *    ak+(k(k-1))=2*2010=
     *    ak+k^2-k= k(k+2a-1)=2*2010
     *
     */
    @Test
    public  void   test3(){

        for (int k = 1; k <2010 ; k++) {
            for (int a = 0; a <2010 ; a++) {
              if(k*(k+2*a-1)==2*2010)  {
                  System.out.println(k);
              }
            }
        }
    }
    /**
     * 3
     * 4
     * 5
     * 12
     * 15
     * 20
     * 60 最大值
     */


}
```