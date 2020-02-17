import org.junit.Test;

/**
 * @ClassName 面试题：53数字在排序数组中出现的次数
 *
 * 题目1：
 * 数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 * 例如：输入排序数组{1,2,3,3,3,3,4,5}
 *和数字3 由于3在数组中出现4次因此输出4
 * 方法：二分查找
 * 复杂度：O(logN)
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2020/2/15  10:57
 * @Version V1.0
 */
public class Solution53_GetFirstK {


    public static void main(String[] args) {


        int[]   arr ={1,2,3,3,3,3,4,5};
//        int firstK = GetFirstK(arr, arr.length, 3, 0, arr.length - 1);
//        System.out.println(firstK);
//        int lastK = GetLask(arr, arr.length, 3, 0, arr.length - 1);
//        System.out.println(lastK);
        int number = GetNumberOfK(arr, arr.length, 3);
        System.out.println(number);

    }
    public    static int  GetFirstK(int[]  data,int length,int k, int   start, int  end ){

      if(start>end){
          return  -1;
      }
        int  mid =(start+end)/2;
        int  midData=data[mid];
        if(data[mid]==k){
            if((mid>0 &&data[mid-1]!=k) || (mid==0)){

             return  mid;

            }else {
                end= mid;//第一个k
            }

        }
        else if(midData>k){//在后半段
            end =mid-1;


        }
        else{//在前半段
            start=mid+1;

        }

        return GetFirstK(data, length, k, start, end);

    }

    /*****
     * 找到最后一个k
     * 1.如果mid的值等于k则判断下一个mid+1 是否是如果相同则进行下一轮
     * 如果mid +1 的不等于k 则mid是最后的一个
     * 2.如果mid的值小于，则找后半段
     * 3.如果mid 的值大于则找前半段
     *
     *
     * @param data  数组
     * @param length  数组长度
     * @param k 目标值
     * @param start  起始位置
     * @param end   结束位置
     * @return  最后一个k 的下标值
     */


  static   int    GetLask(int[]  data,int length,int k, int   start, int  end){

      if(start>end){
          return  -1;
      }
      int  mid =(start+end)/2;
      int  midData=data[mid];
      if(data[mid]==k){
          if((mid<length-1)&& (data[mid+1]!=k) || mid==length-1){

              return  mid;

          }else {
              start= mid;//在后半段
          }

      }
      else if(midData>k){//在前半段
          start =mid+1;


      }
      else{//在前半段
          end=mid-1;

      }

      return GetLask(data, length, k, start, end);

    }

    public  static int  GetNumberOfK(int[] data,int length,int k){

     int  count =0;
      if(data!=null||  data.length>0){
          int first =GetFirstK(data,data.length,k,0,data.length-1);
          int last =GetLask(data,data.length,k,0,data.length-1);
          count=   last-first+1;
      }
      return  count;
    }


    //  特殊测试用例 只有一个数字的数组判断需要添加 mid=0  和mid=length-1的情况
    @Test
    public   void   test1(){

        int[]   arr ={1};

        int number = GetNumberOfK(arr, arr.length, 1);
        System.out.println(number);
    }

    /**
     * 题目2 ：0-n-1 中缺失的数字
     * 一个长度为n-1的递增排序数组所有的数字都是唯一的，并且每个数字都在范围 0-n-1内，在范围0~ n-1内的n个数字有且只有一个数
     * 不在该数组，请找出这个数。
     * O（N）复杂度解法：没有有效利用递增排序的特点
     * n=4  数组长度3：   0   1     3   缺失2
     * n=4  数组长度3：   1,2,3    缺失0
     * n=4  的所有数字之和：1+2+3  之和 s1=(1+n)*n/2
     *    缺失的数字之和为s2
     *    则缺失的数s1-s2
     * 解法2：利用排序数组特点
     *因为数组是排序的
     * 0 在0下标
     * 1在下标
     * 如果m 不在数组里面那么所有比m 小的都在对应下标位置。
     * m+1 在m的位置，则转为排序数组中第一个不在下标位置的数。
     * 利用二分查找的方法
     */

}
