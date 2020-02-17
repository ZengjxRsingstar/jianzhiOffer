```


```

# 第5章

## 53.数字在排序数组中出现的次数

```
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
public class Solution5_GetFirstK {


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
}

```

### 53-2 0~n-1 缺失数

```
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
     * 利用二分查找的方法找到第一个下标不相同的位置。
     * 1.如果中间元素与下标相等：则下次找右半边
     * 2.如果中间元素与下标不相同，并且前一个元素也和它的下标不相同则找左半边。
     */

    public   static  int   getMissingNumber(int[]number,int length,int start,int end){

     if(number==null||length<=0){
         return  -1;
     }
     if(start>end){
         if(end>0 &&number[end]==end &&number[end-1]==end-1){
             return end+1;
         }else if(end==0 &&length==2){
            return 1-number[end];
         }
     }
     int  mid =(start+end)/2;// 0+2 /2


        if(number[mid]==mid){//中间元素与下标相同，找右边。

       //  getMissingNumber(number, length,mid+1,end);
          start=mid+1;//2 // start=3
     }
     else if( mid>0 &&number[mid]!=mid  &&number[mid-1]!=mid-1){//前半部分查找// mid >0  number[2]=3
        // getMissingNumber(number, length, start, mid-1);
         end=mid-1;

     }else if(mid>0 &&   number[mid]!=mid && number[mid-1]==mid-1)//找到该元素 mid
        return  mid;// mid =2    mid =1
     else if(mid==0 && number[mid]!=0 &&number[mid+1]!=mid+1 ){
         return 0;
     }
     return  getMissingNumber(number, length, start,end);//   start =2 ,end=3
    }
    @Test
    public   void   test2(){
       //缺失的数字在在中间
        int[] arr1={0,1,3};
        System.out.println(getMissingNumber(arr1,arr1.length+1,0,arr1.length-1));//pass
       //缺失的数字在的第一个
        int[] arr2={1,2,3};
        System.out.println(getMissingNumber(arr2,arr2.length+1,0,arr2.length-1));//pass
      //缺失的数字在末尾
        int[] arr3={0,1,2};
        System.out.println(getMissingNumber(arr3,arr3.length+1,0,arr3.length-1));//pass
     // 只有一个数字
      int[] arr4={0};
        System.out.println("只有一个数字"+getMissingNumber(arr4,arr4.length+1,0,arr4.length-1));//pass

    }
    //书上方法
    int  getMissingnumber2(int[] numbers ,int length){
        if(numbers==null ||length<=0){
            return  -1;
        }
        int left =0;
        int right=length-1;

        while (left<=right){
            int middle=(right+left)>>1;
            if(numbers[middle]!=middle){//中间值与下标不相同
                if(middle==0 || numbers[middle-1]==middle-1){
                    return middle;
                }
                right=middle-1;//否则往左边找

            }else{//右边找
                left=middle+1;
            }
        }
        if(left==length){
            return length;
        }
        return -1;
    }



    @Test
    public   void   test3(){
        //缺失的数字在在中间
        int[] arr1={0,1,3};
        System.out.println(getMissingNumber(arr1,arr1.length+1,0,arr1.length-1));//pass
        //缺失的数字在的第一个
        int[] arr2={1,2,3};
        System.out.println(getMissingNumber(arr2,arr2.length+1,0,arr2.length-1));//pass
        //缺失的数字在末尾
        int[] arr3={0,1,2};
        System.out.println(getMissingNumber(arr3,arr3.length+1,0,arr3.length-1));//pass
        // 只有一个数字
        int[] arr4={0};
        System.out.println("只有一个数字"+getMissingNumber(arr4,arr4.length+1,0,arr4.length-1));//pass

    }

    @Test
    public   void   test4(){
        //缺失的数字在在中间
        int[] arr1={0,1,3};
        System.out.println(getMissingnumber2(arr1,arr1.length));//pass
        //缺失的数字在的第一个
        int[] arr2={1,2,3};
        System.out.println(getMissingnumber2(arr2,arr2.length));//pass
        //缺失的数字在末尾
        int[] arr3={0,1,2};
        System.out.println(getMissingnumber2(arr3,arr3.length));//pass
        // 只有一个数字
        int[] arr4={0};
        System.out.println("只有一个数字"+getMissingnumber2(arr4,arr4.length));//pass

    }

```

