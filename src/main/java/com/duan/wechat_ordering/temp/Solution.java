package com.duan.wechat_ordering.temp;

import lombok.Data;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.relational.core.sql.In;
import sun.nio.cs.ext.MacHebrew;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
class vo1{
    public String name;
    public Integer age;
    public void out(){
        System.out.println("ff");
    }
    @Override
    public String toString() {
        return "name:"+name+"age:"+age;
    }
}
@Data
class vo2{
    public String name;
    public Integer age;
    public String dd;

    @Override
    public String toString() {
        return "name:"+name+"age:"+age+"dd"+dd;
    }
}

//----------------------代理模式---------------------------------
// interface idou{
//      void save();
//}
//class dou implements idou{
//    @Override
//    public void save() {
//        System.out.println("savE________________");
//    }
//}
//class douproxy implements idou{
//    private idou target;
//    public douproxy(idou target){
//        this.target=target;
//    }
//    @Override
//    public void save() {
//        System.out.println("前置---------");
//        target.save();
//        System.out.println("后置---------");
//    }
//
//}
//public class Solution {
//    public static void main(String[] args) {
//        idou a=new dou();
//        idou proxy=new douproxy(a);
//        proxy.save();
//    }
//}

//
//class dynamicproxy implements InvocationHandler{
//    Object obj;
//    public dynamicproxy(Object obj){
//        this.obj=obj;
//    }
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("前置");
//        Object returnvalue=method.invoke(obj,args);
//        System.out.println("后置");
//
//        return returnvalue;
//    }
//}
//interface iuser{
//    void save();
//}
//class user implements iuser{
//    public void save(){
//        System.out.println("save------");
//    }
//}
//class Solution{
//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        iuser u=new user();
//        dynamicproxy proxy=new dynamicproxy(u);
//        iuser i=(iuser) Proxy.newProxyInstance(iuser.class.getClassLoader(),new Class[]{iuser.class},proxy);
//        i.save();
//    }
//}

class Solution {
    public static int maxSubArray(int[] nums) {
        String a="123";
        a= String.valueOf(1234);
        System.out.println(a);
        return 1;


    }
    public int numDecodings(String s) {
        int len=s.length();
        if(s.charAt(0)=='0'){
            return 0;
        }
        int []result=new int[len];
        result[0]=1;
        if(action(s.charAt(0),s.charAt(1))<=26&&s.charAt(1)!='0')result[1]=2;
        else result[1]=1;
        for (int i = 2; i < len; i++) {
            if(s.charAt(i)=='0'){
                if(s.charAt(i-1)=='1'||s.charAt(i-1)=='2'){
                    result[i]=result[i-2];
                }else{
                    return 0;
                }
            }
            else if(action(s.charAt(i-1),s.charAt(i))<=26){
                result[i]=result[i-1]+result[i-2];
            }
            else{
                result[i]=result[i-1];
            }
        }
        return result[len-1];

    }
    public int action(char a,char b){
        return (a-'0')*10+(b-'0');
    }

    public int lengthOfLIS(int[] nums) {
        int len=nums.length;
        int []result=new int[len];
        for (int i = 0; i < len; i++) {
            result[i]=1;
        }
        int count=1,all=1;
        for (int i = 1; i <len  ; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    count=Math.max(count,result[j]+1);
                }
            }
            result[i]=count;all=Math.max(all,count);
        }
        return all;
    }
      public class TreeNode {
     int val;
     TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> list=new ArrayList<>();
//        if(root==null)return list;
//        record(root,list);
//        return list;
//    }
//    public void record(TreeNode root,List<Integer>list){
//        if(root.left!=null){
//            record(root.left,list);
//        }
//        list.add(root.val);
//        if(root.right!=null){
//            record(root.right,list);
//        }
//    }

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        int height=0;
//        List<List<Integer>> lists=new ArrayList<>();
//        if(root==null)return lists;
//        action(root,height,lists);
//        return lists;
//    }
//    public void action(TreeNode root,int height,List<List<Integer>> lists){
//        if(height==lists.size()){//如果是初次访问，那就要新建一个list
//            List<Integer>list=new ArrayList<>();
//            lists.add(list);
//        }
//        List<Integer> list=lists.get(height);
//        list.add(root.val);
//        if(root.left!=null) action(root.left,height+1,lists);
//        if(root.right!=null) action(root.right,height+1,lists);
//    }


//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        int height=0;
//        List<List<Integer>> lists=new ArrayList<>();
//        if(root==null)return lists;
//        action(root,height,lists);
//        return lists;
//    }
//    public void action(TreeNode root,int height,List<List<Integer>> lists){
//        if(height==lists.size()){//如果是初次访问，那就要新建一个list
//            List<Integer>list=new ArrayList<>();
//            lists.add(list);
//        }
//        List<Integer> list=lists.get(height);
//
//        if(height%2==0)
//            list.add(root.val);
//        else {
//            list.add(0,root.val);
//        }
//        if(root.left!=null) action(root.left,height+1,lists);
//        if(root.right!=null) action(root.right,height+1,lists);
//    }



//    public List<List<Integer>> pathSum(TreeNode root, int sum) {
//        List<List<Integer>> lists=new ArrayList<>();
//        if(root==null)return lists;
//        List<Integer>list=new ArrayList<>();
//        action(root,sum,list,lists);
//        return lists;
//    }
//    public void action(TreeNode root,int sum,List<Integer>list,List<List<Integer>> lists){
//        list.add(root.val);
//        //如果是叶子节点
//        if(root.left==null&&root.right==null){
//            int all=0;
//            for(Integer integer:list){
//                all+=integer;
//            }
//            if(all==sum) {
//                List<Integer>newlist=new ArrayList<>();
//                newlist.addAll(list);
//                lists.add(newlist);
//                return;
//            }
//        }
//        if(root.left!=null){
//            action(root.left,sum,list,lists);
//        }
//        if(root.right!=null){
//            action(root.right,sum,list,lists);
//        }
//        list.remove(list.size()-1);
//    }




//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        if(root==null) {
//            root=new TreeNode(val);
//            return root;
//        }
//        action(root,val);
//        return root;
//    }
//    public void action(TreeNode root,int val){
//        if(root.val<val){
//            if(root.right!=null){//右节点存在的话
//                action(root.right,val);
//            }
//            else{
//                TreeNode node=new TreeNode(val);
//                root.right=node;
//            }
//        }
//        else if(root.val>val){
//            if(root.left!=null){//左节点存在的话
//                action(root.left,val);
//            }
//            else{
//                TreeNode node=new TreeNode(val);
//                root.left=node;
//            }
//        }
//    }





//    public static String countAndSay(int n) {
//        StringBuilder sb=new StringBuilder("11");
//        if(n==1)return "1";
//        if(n==2 )return "11";
//        for (int i = 0; i < n-2; i++) {
//            sb=record(sb);
//        }
//        return sb.toString();
//    }
//
//    public static StringBuilder record(StringBuilder sb){
//        StringBuilder newsb=new StringBuilder();
//        char c=sb.charAt(0);
//        int count=1;
//        for(int i=1;i<sb.length();i++){
//            while (sb.charAt(i)==c){
//                count++;
//                i++;
//                if(i==sb.length()){
//                    newsb.append((char)('0'+count));
//                    newsb.append(c);
//                    return newsb;
//                }
//            }
//            newsb.append((char)('0'+count));
//            newsb.append(c);
//            c=sb.charAt(i);
//            count=1;
//        }
//        newsb.append((char)('0'+count));
//        newsb.append(c);
//        return newsb;
//    }


//    public static int action(char a){
//        if(a=='I'){
//            return 1;
//        }else if(a=='V'){
//            return 5;
//        }else if(a=='X'){
//            return 10;
//        }else if(a=='L'){
//            return 50;
//        }else if(a=='C'){
//            return 100;
//        }else if(a=='D'){
//            return 500;
//        }else {
//            return 1000;
//        }
//
//    }
//    public static int romanToInt(String s) {
//        StringBuilder sb=new StringBuilder(s);
//        int all=0;
//        for (int i = 0; i < sb.length(); i++) {
//            char c=sb.charAt(i);
//            if(i==sb.length()-1){
//                return all+action(c);
//            }
//            else {
//                if(action(c)<action(sb.charAt(i+1))){
//                    i++;
//                    all=all-action(c)+action(sb.charAt(i));
//                }
//                else{
//                    all+=action(c);
//                }
//            }
//        }
//        return all;
//    }






//    public List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> lists=new ArrayList<>();
//        Arrays.sort(nums);//-4 -1 -1 0 1 2 2
//        if(nums[0]>0)return lists;
//        int len=nums.length;
//        for (int i = 0; i < len-2; i++) {
//            int left=i+1,right=len-1;
//            if(nums[i]>0)return lists;
//            if(i>0&&nums[i]==nums[i-1]){
//                continue;
//            }
//            while(left<right){
//                int sum=nums[i]+nums[left]+nums[right];
//                if(sum==0){
//                    while(left<right&&nums[left]==nums[left+1]){
//                        left++;
//                    }
//                    while(left<right&&nums[right]==nums[right-1]){
//                        right--;
//                    }
//                    List<Integer>list=new ArrayList<>();
//                    list.add(nums[i]);list.add(nums[left]);list.add(nums[right]);
//                    lists.add(list);
//                    left++;right--;
//                }
//                else if(sum<0){
//                    left++;
//                }
//                else{
//                    right--;
//                }
//            }
//        }
//        return lists;
//
//    }







//    public static int threeSumClosest(int[] nums, int target) {
//        if(nums.length<3)return 0;
//        int len=nums.length;
//        Arrays.sort(nums);//-4 -1 1 2  tar=1   -3 0 1 2
//        int min=Integer.MAX_VALUE;
//        int result=0;
//        for (int i = 0; i < len-2; i++) {
//            int left=i+1,right=len-1;
//            while (left<right){
//                int sum=nums[i]+nums[left]+nums[right];
//                int close=Math.abs(target-sum);
//                min=Math.min(close,min);
//                result=(min==close)?sum:result;
//                if(target>sum){
//                    left++;
//                }
//                else if(target<sum){
//                    right--;
//                }
//                else{
//                    return target;
//                }
//            }
//        }
//        return result;
//    }





//    public int lengthOfLongestSubstring(String s) {
//        //滑动窗口
//        if(s.length()<=1)return s.length();
//        int max=0;int start=0;
//        int result=Integer.MIN_VALUE;
//        Map<Integer,Character> map=new HashMap<>();
//        Set<Character> set=new HashSet<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c=s.charAt(i);
//            if(set.contains(c)){
//                int j;
//                for ( j = start; set.contains(c) ; j++) {
//                    set.remove(s.charAt(j));
//                }
//                start=j;set.add(c);
//                max=set.size();
//            }
//            else{
//                set.add(c);
//                max++;
//                result=Math.max(max,result);
//            }
//
//        }
//        return result;
//    }

//    public static int[] twoSum(int[] nums, int target) {
////        HashMap<Integer,Integer> map=new HashMap<>();
////        for (int i = 0; i <nums.length ; i++) {
////            if(map.containsKey(nums[i])&&nums[i]*2==target){
////                return new int[]{map.get(nums[i]),i};
////            }
////            map.put(nums[i],i);
////        }
////        for (int i = 0; i < nums.length; i++) {
////            int need=target-nums[i];
////            if(map.containsKey(need)&&(need*2!=target)){
////                return new int[]{i,map.get(need)};
////            }
////        }
////        return new int[]{-1,-1};
////    }


      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }


//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        if(l1.val==0)return l2;
//        if(l2.val==0)return l1;
//        ListNode listNode = new ListNode(0),p=null,q=null;
//        p=listNode;p.next=q;
//        int add=0;
//        for(;l1==null&&l2==null;l1=l1.next,l2=l2.next){
//            int val1=0,val2=0;
//            if(l1!=null)val1=l1.val;
//            if(l2!=null)val2=l2.val;
//            int result=val1+val2+add;
//            add=0;
//            if(result>=10){
//                q=new ListNode(result-10);
//                q.next=null;
//                add=1;
//            }
//            else{
//                q=new ListNode(result);
//                q.next=null;
//            }
//            p.next=q;
//            p=q;
//            q=q.next;
//        }
//        return listNode;
//
//    }

//    public int rob(int[] nums) {
//        int len=nums.length;
//        int []dp=new int[len];
//        dp[0]=nums[0];
//        dp[1]=Math.max(nums[0],nums[1]);
//        for (int i = 2; i < len; i++) {
//            dp[i]=Math.max(dp[i-1],nums[i]+dp[i-2]);
//        }
//        return dp[len-1];
//    }






//    public int rob(int[] nums) {
//        int len=nums.length;
//        if(len==0)return 0;
//        if(len==1)return nums[0];
//        return Math.max(action(nums,0,len-2),action(nums,1,len-1));
//    }
//    public int action(int[] nums,int start,int end ){
//        int []dp=new int[end-start+1];
//        dp[start]=nums[start];
//        dp[start+1]=Math.max(nums[start],nums[start+1]);
//        for(int i=start+2;i<=end;i++){
//            dp[i]=Math.max(dp[i-1],nums[i]+dp[i-2]);
//        }
//
//        return dp[end];
//    }

//    public int countDigitOne(int n) {
//        // __0__ 0位置出现1的次数为 high*digit
//        // __1__ 1位置出现1的次数为 high*digit+low+1
//        // __>1__ >1位置出现1的次数为 （high+1)*digit
//
//        int high=n/10,digit=1,low=0,now=n%10;
//        int all=0;
//        while (low<n){
//            if(now==0){
//                all+=high*digit;
//            }else if(now==1){
//                all+=high*digit+low+1;
//            }
//            else{
//                all+=(high+1)*digit;
//            }
//            low=now*digit+low;
//            now=high%10;
//            high/=10;
//            digit*=10;
//        }
//        return all;
//    }



//    public int maxProfit(int[] prices) {
//        int len=prices.length;
//        if(len<=1)return 0;
//        int result=0;int min=prices[0];
//        for (int i = 1; i < len; i++) {
//            result=Math.max(result,prices[i]-min);
//            min=Math.min(prices[i],min);
//        }
//        return result;
//    }


//    public  int[] firstbigger(int []nums){
//        //单调栈来做，这里要注意的 就是 放在栈里的，不要放具体的数字45，37，而应该放标号，
//        // 因为已经有nums数组了，可以通过标号来找到这个数字。
//        int len=nums.length;
//        if(len==0)return new int[]{};
//        int []result=new int[len];
//        for (int i = 0; i < len; i++) {
//            result [i]=-1;
//        }
//        Stack<Integer> stack=new Stack<>();
//        stack.push(0);
//        for (int i = 1; i < len; i++) {
//            int now=nums[i];
//            if(now>nums[stack.peek()]){
//                while (!stack.isEmpty()&&now>nums[stack.peek()]){
//                    result[stack.peek()]=now;
//                    stack.pop();
//                }
//                stack.push(i);
//            }
//            else{
//                stack.push(i);
//            }
//        }
//        return result;
//    }



    public static int binary_search(int []nums){
        //有序数组    找寻第一个正数
//        int left=0,right=nums.length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]<=0){
//                left=mid+1;
//            }
//            else if(nums[mid]>0){
//                right=mid;
//            }
//        }
//        return right;
        //查找最后一个负数
        int left=0,right=nums.length;
        while (left<right) {
            int mid=left+(right-left+1)/2;
            if(mid>=nums.length)return nums.length-1;
            if(nums[mid]>=0){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left;
    }
    public int mySqrt(int x) {
        if(x<=1)return x;
        long left=0,right=x;
        while (left<right){
            long mid=left+(right-left+1)/2;
            if(mid*mid>x){
                right=mid-1;
            }
            else if(mid*mid==x){
                return (int)(mid);
            }
            else{
                left=mid;
            }
        }
        return (int)left;

    }



//
//    public int maxArea(int[] height) {
//        int left=0,right=height.length;
//        int result=0;
//        while (left<right){
//            result= Math.max(result,Math.min(height[left],height[right])*(right-left));
//            if(height[left]<=height[right]){
//                    left++;
//            }else {
//                right++;
//            }
//        }
//        return result;
//    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists=new ArrayList<>();
        if (nums.length<4)return lists;
        Arrays.sort(nums);
        int left=0,k,i,j;
        while (left<=nums.length-4){
            i=left+1;
            while (i<=nums.length-3){
                j=i+1;k=nums.length-1;
                while (j<k){
                    int all=target-nums[left]-nums[i]-nums[k]-nums[j];
                    if(all==0){
                        List<Integer>list=new ArrayList<>();
                        list.add(nums[left]);
                        list.add(nums[i]);list.add(nums[j]);list.add(nums[k]);
                        lists.add(list);
                        do{
                            k--;
                        }while (k>0&&nums[k]==nums[k+1]);
                        do {
                            j++;
                        }while (j<nums.length&&nums[j]==nums[j-1]);
                    }else if(all<0){
                        k--;
                    }else {
                        j++;
                    }
                }
                do{
                    i++;
                }while (i<nums.length&&nums[i]==nums[i-1]);
            }
            do {
                left++;
            }while (left>0&&left<nums.length&&nums[left]==nums[left-1]);
        }
        return lists;

    }

//    public int firstMissingPositive(int[] nums) {
//        Arrays.sort(nums);
//        if(nums[nums.length-1]<=0)return 1;
//        int i=search_positive(nums);
//        if (nums[i]==1){
//            int number=1;
//            while (true){
//                i++;number++;
//                if(i==nums.length)return number;
//                 if(nums[i]!=number)return number;
//            }
//        }else return 1;
//    }
//    public  int search_positive(int []nums){
//        //有序数组    找寻第一个正数和最后一个负数
//        int left=0,right=nums.length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]<=0){
//                left=mid+1;
//            }
//            else if(nums[mid]>0){
//                right=mid;
//            }
//        }
//        return right;
//    }






//    public int findRepeatNumber(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i]!=nums[nums[i]]){
//                swap(nums,nums[i],nums[nums[i]]);
//            }
//            if(i!=nums[i])return nums[i];
//        }
//        return -1;
//    }
//    private void swap(int []nums,int i,int j){
//        int temp=nums[i];
//        nums[i]=nums[j];
//        nums[j]=temp;
//    }


//    public boolean findNumberIn2DArray(int[][] matrix, int target) {
//        int n=matrix.length;
//        int m=matrix[0].length;
//        int i=0,j=m-1;
//        while (i<n&&j>=0){
//            if(matrix[i][j]>target)j--;
//            else if(matrix[i][j]<target)i++;
//            else return true;
//        }
//        return false;
//    }



//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return action(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
//    }
//    public TreeNode action(int[] preorder, int[] inorder,int l1,int l2,int r1,int r2){
//        if(l1>l2)return null;
//        int rootval=preorder[l1];
//        TreeNode treeNode=new TreeNode(rootval);
//        int inorder_root=0;
//        for ( int i = r1; i <=r2     ; i++) {
//            if(inorder[i]==rootval)inorder_root=i;
//        }
//        treeNode.left=action(preorder,inorder,l1+1,l1+inorder_root-r1,r1,inorder_root-1);
//        treeNode.right=action(preorder,inorder,l1+inorder_root-r1+1,l2,inorder_root+1,r2);
//        return  treeNode;
//    }





//    public int numWays(int n) {
//        int []dp=new int[n+1];
//        dp[1]=1;dp[2]=2;
//        for (int i = 3; i <=n; i++) {
//            dp[i]=dp[i-1]+dp[i-2];
//        }
//        return dp[n];
//    }



//    public int search(int[] nums, int target) {
//        int left=0,right=nums.length-1;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]==nums[right]){
//                right--;
//            }else if(nums[mid]<nums[right]){
//                right=mid;
//            }else {
//                left=mid+1;
//            }
//        }
//        if(left==0){//1 2 3 4 5这种情况
//            return binary_search(nums,target,0,nums.length-1);
//        }else{
//            if(target>nums[nums.length-1])//左半边找
//            return binary_search(nums,target,0,left-1);
//            else return binary_search(nums,target,left,nums.length-1);
//        }
//    }
//    public int binary_search(int []nums,int target,int i,int j){
//        int left=i,right=j;
//        while (left<=right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]==target){
//                return mid;
//            }else if(nums[mid]<target){
//                left=mid+1;
//            }else{
//                right=mid-1;
//            }
//        }
//        return -1;
//    }




//
//    public ListNode deleteNode(ListNode head, int val) {
//        if(head==null)return head;
//        ListNode newhead=new ListNode(0);
//        newhead.next=head;
//        ListNode p=newhead,q=head;
//        while (q!=null&&q.val!=val){
//            p=p.next;q=q.next;
//        }
//        if(q==null){
//            return head;
//        }else {
//            p.next=q.next;
//            return head;
//        }
//    }


//
//    public ListNode getKthFromEnd(ListNode head, int k) {
//        int len=0;
//        ListNode p=head,q;
//        while (p!=null){
//            p=p.next;len++;
//        }
//        k=k%(len+1);
//        ListNode node=new ListNode(0);
//        node.next=head;p=node;q=node.next;
//        for(;k>0;k--){
//            q=q.next;
//        }
//        while (q!=null){
//            q=q.next;p=p.next;
//        }
//        return p.next;
//
//    }



//    public ListNode reverseList(ListNode head) {
//        ListNode p=head,q,r;
//        if(p==null)return p;
//        q=p.next;
//        if(q==null)return p;
//        r=q.next;
//        while (r!=null){
//            if(p==head){
//                p.next=null;
//            }
//            q.next=p;
//            p=q;q=r;r=r.next;
//        }
//        q.next=p;
//        return q;
//    }



//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dum=new ListNode(0),temp=dum;
//        while (l1!=null||l2!=null){
//            if(l1.val<=l2.val){
//                dum.next=l1;
//                l1=l1.next;
//            }else {
//                dum.next=l2;
//                l2=l2.next;
//            }
//            dum=dum.next;
//        }
//        return temp.next;
//    }

//                             5
//                            / \
//                           4   8
//                          /   / \
//                        11  13   4
//                        /  \    / \
//                      7    2  5   1




//    public List<List<Integer>> pathSum(TreeNode root, int sum) {
//        List<List<Integer>> lists=new ArrayList<>();
//        List<Integer> list=new ArrayList<>();
//        if(root==null)return lists;
//        action(root,sum,lists,list);
//        return lists;
//
//    }
//    public void action(TreeNode root, int sum,List<List<Integer>> lists,List<Integer> list){
//        list.add(root.val);
//        if(root.left==null&&root.right==null){
//            int all=0;
//            for(Integer a:list){
//                all+=a;
//            }
//            if(all==sum){
//                List<Integer>temp=new ArrayList<>();
//                temp.addAll(list);
//                lists.add(temp);
//            }
//        }else{
//            if(root.left!=null) action(root.left,sum,lists,list);
//            if(root.right!=null) action(root.right,sum,lists,list);
//        }
//        list.remove(list.size()-1);
//    }



//    public int missingNumber(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i]<nums.length&&nums[i]!=nums[nums[i]]){
//                int temp=nums[i];
//                nums[i]=nums[nums[i]];
//                nums[temp]=temp;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i]!=i)return i;
//        }
//        return -1;
//    }




//    public int lengthOfLongestSubstring(String s) {
//        int max=0;
//        Map<Character,Integer>map=new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c=s.charAt(i);
//            if(map.containsKey(c)){//如果包含重复的key
//                int temp=map.get(c);
//                Iterator<Map.Entry<Character,Integer>>iterator=map.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<Character,Integer>entry=iterator.next();
//                    if(entry.getValue()<=temp)iterator.remove();
//                }
//                map.put(c,i);
//            }else {//如果不包含key
//                map.put(c,i);
//                max=Math.max(max,map.size());
//            }
//        }
//        return max;
//    }

//    s = "abaccdeff"
//    返回 "b"
//
//    s = ""
//    返回 " "
//    public char firstUniqChar(String s) {
//        Map<Character,Integer>map=new LinkedHashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c=s.charAt(i);
//            if(map.containsKey(c)){
//                int oldval=map.get(c);
//                map.replace(c,oldval+1);
//            }else {
//                map.put(c,1);
//            }
//        }
//        char c=' ';
//        Iterator<Map.Entry<Character,Integer>>it=map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Character, Integer> next =  it.next();
//            if(next.getValue()==1){
//                c=next.getKey();
//                break;
//            }
//     }
//        return c;
//    }


//    public int cuttingRope(int n) {
//        int []dp=new int[n+1];
//        if(n<=1)return n;
//        dp[1]=1;dp[2]=2;
//        for (int time = 2; time <= n; time++) {
//            for (int i = 1; i <=time-1 ; i++) {
//                int j=time-i;
//                dp[time]=Math.max(Math.max(i*j,i*dp[j]),dp[time]);
//            }
//        }
//        return dp[n];
//    }


//    public int flag=0;
//    public boolean verifyPostorder(int[] postorder) {
//        action(postorder,0,postorder.length-1);
//        return flag==0?true:false;
//    }
//    public void action(int[] postorder,int left,int right){
//        if (left>=right)return ;
//        int i=left,j=left;
//        for(;j<right;j++)if(postorder[j]>postorder[right])break;
//        if(j==right)return;
//        int max=max(postorder,i,j-1);
//        int min=min(postorder,j,right-1);
//        if(max<postorder[right]&&min>postorder[right]){//符合
//            action(postorder,i,j-1);
//            action(postorder,j,right-1);
//        }else{//不符合
//            flag=1;
//        }
//    }
//    public int max(int[] postorder,int left,int right){
//
//        int max=postorder[left];
//        for (int i = left; i <=right ; i++) {
//            max=Math.max(max,postorder[i]);
//        }
//        return max;
//    }
//    public int min(int[] postorder,int left,int right){
//        int min=postorder[left];
//        for (int i = left; i <=right ; i++) {
//            min=Math.min(min,postorder[i]);
//        }
//        return min;
//    }





    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        Queue<TreeNode> que=new LinkedList<>();
//        List<String>list=new ArrayList<>();
//        if(root==null) return "null";
//        que.add(root);
//        while (!que.isEmpty()){
//            int size=que.size();
//            while ((size--)>0){
//                TreeNode node=que.remove();
//                if(node!=null){
//                    list.add(String.valueOf(node.val));
//                    que.add(node.left);
//                    que.add(node.right);
//                }else{
//                    list.add("null");
//                }
//            }
//        }
//        StringBuilder sb=new StringBuilder();
//       // sb.append("[");
//        for (String s : list) {
//            sb.append(s+",");
//        }
//        sb.deleteCharAt(sb.length()-1);
//       // sb.append("]");
//        return sb.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        String []list=data.split(",");
//        Queue<TreeNode>que=new LinkedList<>();
//        if(list[0].equals("null"))return null;
//        TreeNode root=new TreeNode(Integer.parseInt(list[0]));
//        que.add(root);
//        int i=1;
//        while (!que.isEmpty()){
//            TreeNode node=que.poll();
//            if(!list[i].equals("null")){
//                node.left=new TreeNode(Integer.parseInt(list[i]));
//                que.add(node.left);
//            }
//            else {
//                node.left=null;
//            }
//            i++;
//            if(!list[i].equals("null")){
//                node.right=new TreeNode(Integer.parseInt(list[i]));
//                que.add(node.right);
//            }else {
//                node.right=null;
//            }
//            i++;
//        }
//        return root;
//    }


    //"abc"   "abb"
//    public Set<String>set=new HashSet<>();
//    public String[] permutation(String s) {
//        char []c=s.toCharArray();
//        int len=s.length();
//        boolean[] flag=new boolean[len];//默认为false
//        Arrays.sort(c);
//        StringBuilder sb=new StringBuilder();
//        int height=0;
//        action(c,flag,sb,height);
//        String []ss=new String[set.size()];
//        Iterator<String>it=set.iterator();
//        int i=0;
//        while (it.hasNext()) {
//            String next =  it.next();
//            ss[i]=new String(next);
//            i++;
//        }
//        return ss;
//    }
//    public void action(char []c,boolean []flag,StringBuilder sb,int height){
//        if(height==c.length){
//            String s=sb.toString();
//            set.add(s);
//            return;
//        }
//        for (int i = 0; i < c.length; i++) {
//            if(!flag[i]){
//                sb.append(c[i]);
//                flag[i]=true;
//                action(c,flag,sb,height+1);
//                flag[i]=false;
//                sb.deleteCharAt(sb.length()-1);
//            }
//        }
//    }



    public String minNumber(int[] nums) {
        
        //String []s=new String[nums.length];
        List<String>list=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1)>0?1:-1;
//                char c1=o1.charAt(0),c2=o2.charAt(0);
//                if(c1==c2){
//                    int len1=o1.length(),len2=o2.length();
//                    if(len1==len2){
//                        int i=1;
//                        while (i<len1){
//                            char ctemp1=o1.charAt(i),ctemp2=o2.charAt(i);
//                            if(ctemp1!=ctemp2)return ctemp1>ctemp2?1:-1;
//                            i++;
//                        }
//                        return 0;//两个完全相等 随便取
//                    }else{
//                        int len=Math.min(len1,len2);
//                        int i=1;
//                        while (i<len){
//                            char ctemp1=o1.charAt(i),ctemp2=o2.charAt(i);
//                            if(ctemp1!=ctemp2)return ctemp1>ctemp2?1:-1;
//                            i++;
//                        }
//                        if(len1<len2){
//                            return c1<o2.charAt(i)?-1:1;
//                        }else{
//                            return c2<o1.charAt(i)?1:-1;
//                        }
//                    }
//                }else {
//                    return c1>c2?1:-1;
//                }
            }
        });
        String s1="";
        for (String s : list) {
            s1+=s;
        }
        return s1;
    }




//    public int maxValue(int[][] grid) {
//        int len1=grid.length,len2=grid[0].length;
//        if(len1==0)return 0;
//        int [][]dp=new int[len1][len2];
//        dp[0][0]=grid[0][0];
//        for (int i = 1; i < len1; i++) {
//            dp[i][0]=grid[i][0]+dp[i-1][0];
//        }
//        for (int i = 1; i < len2; i++) {
//            dp[0][i]=grid[0][i]+dp[0][i-1];
//        }
//        for (int i = 1; i < len1; i++) {
//            for (int j = 1; j < len2; j++) {
//                dp[i][j]=grid[i][j]+Math.max(dp[i-1][j],dp[i][j-1]);
//            }
//        }
//        return dp[len1-1][len2-1];
//    }



//    public int min=0;
//    public int now=0;
//    public int kthLargest(TreeNode root, int k) {
//        if(root==null)return 0;
//        action(root,k);
//        return min;
//    }
//    public void action(TreeNode root, int k){
//        if(root.right!=null){
//            action(root.right,k);
//        }
//        now++;
//        if(now==k){
//            min=root.val;
//            return;
//        }
//        if(root.left!=null){
//            action(root.left,k);
//        }
//    }


    public int[] singleNumbers(int[] nums) {
        int all=nums[0];
        for (int i = 1; i < nums.length; i++) {
            all=all^nums[i];
        }
        int bitmask=1;
        for (int i = 0; i < 32; i++) {
            if((all&bitmask)!=0){
                break;
            }
            bitmask=bitmask<<1;
        }
        //int a=0,b=0;//分为两组  tempi为1的在a组
        List<Integer>a=new ArrayList<>();
        List<Integer>b=new ArrayList<>();
        for(int num:nums){
            if((num&bitmask)!=0){
                a.add(num);
            }else {
                b.add(num);
            }
        }
        int res1=a.get(0),res2=b.get(0);
        for (int i = 1; i < a.size(); i++) {
            res1=res1^a.get(i);
        }
        for (int i = 1; i < b.size(); i++) {
            res2=res2^b.get(i);
        }
        return new int[]{res1,res2};
    }

//    public int singleNumber(int[] nums) {
//        if(nums.length==1)return nums[0];
//        int bitmask=1;
//        int []bitnum=new int[32];
//        for(int num:nums){
//            for (int i = 0; i < 32; i++) {
//                bitnum[i]+=(num&bitmask)!=0?1:0;
//                bitmask=bitmask<<1;
//            }
//            bitmask=1;
//        }
//        int res=0;
//        for (int i = 0; i < 32; i++) {
//            bitnum[i]=bitnum[i]%3;
//            res+=bitnum[i]*Math.pow(2,i);
//        }
//        return res;
//    }


    public int sumNums(int n) {
        return action(n,1);
    }
    public int action(int n,int i){
        if(i<=n)
        return i+action(n,i+1);
        else return 0;
    }

    public int hammingWeight(int n) {
        int bitmask=1;
        int res=0;
        for (int i = 0; i < 32; i++) {
            if((n&bitmask)!=0){
                res++;
            }
            bitmask=bitmask<<1;
        }
        return res;
    }



//    public int add(int a, int b) {
//        int jinwei=(a&b)<<1,nonejinwei=a^b;
//        while (true){
//            a=a^b;//非进位
//            b=(a&b)<<1;//进位
//            if(b==0){
//                break;
//            }
//
//        }
//        return a;
//    }





    public void mergesort(int []a,int start,int end){
        if(start<end){
            int mid=(start+end)/2;
            mergesort(a,start,mid);
            mergesort(a,mid+1,end);
            merge(a,start,mid,end);
        }
    }
    public void merge(int[]a,int left,int mid,int right ){
        int []temp=new int[right-left+1];
        int p1=left,p2=mid+1,k=0;
        while (p1<=mid&&p2<=right){
            if(a[p1]<=a[p2])temp[k++]=a[p1++];
            else temp[k++]=a[p2++];
        }
        while (p1<=mid)temp[k++]=a[p1++];
        while (p2<=right)temp[k++]=a[p2++];
        k=0;
        for (int i = left; i <=right ; i++) {
            a[i]=temp[k];
            k++;
        }
    }

    public void quicksort(int []a,int start,int end){
        if(start<end){
            int  base=a[start],left=start,right=end;
            while (left<right){
                while (left< right&&a[right]>=base)right--;
                while (left<right&&a[left]<=base)left++;
                if(left<right){
                    int temp=a[left];
                    a[left]=a[right];
                    a[right]=temp;
                }
            }
            a[start]=a[left];
            a[left]=base;
            quicksort(a,start,left-1);
            quicksort(a,left+1,end);
        }
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        return action(arr,0,arr.length-1,k);
    }
    public int[] action(int[] arr, int start,int end,int k){
        int position=myquick(arr,start,end);
        System.out.println(position);
        if(position==k){
            int a[]=new int[k];
            for (int i = 0; i < k; i++) {
                a[i]=arr[i];
            }
            return a;
        }else if(position<k){
            return action(arr,position+1,end,k);
        }else {
            return action(arr,start,position-1,k);
        }
    }
    public int myquick(int[] arr, int start,int end){
        if(start<end){
            int base=arr[start],left=start,right=end;
            while (left<right){
                while (left<right&&arr[right]>=base)right--;
                while (left<right&&arr[left]<=base)left++;
                if(left<right){
                    int temp=arr[left];
                    arr[left]=arr[right];
                    arr[right]=temp;
                }
            }
            arr[start]=arr[left];
            arr[left]=base;
            return left;
        }
        return start;
    }

    public double[] twoSum(int n) {
        int [][]dp=new int[n+1][n*6+6];
        for (int i = 1; i <=6   ; i++) {
            dp[1][i]=1;
        }
        int before_maxj=6;
        int before_minj=1;
        for (int i = 2; i <=n ; i++) {
            int maxj=before_maxj+6;
            int minj=before_minj+1;
            int j=maxj;
            while (j>=minj){
                dp[i][j]=0;
                for (int k = 1; k <=6&&j-k>=1; k++) {
                    dp[i][j]+=dp[i-1][j-k];
                }
                j--;
            }
        }
        int start=0,len=0,all=0;
        boolean flag=true;
        for (int i = 0; i < dp[0].length; i++) {
            if(dp[n][i]!=0){
                if(flag) {
                    start = i;
                    flag=false;
                }
                all+=dp[n][i];
            }
        }
        len=(n)*6-start+1;
        double[] res=new double[len];
        for (int i = 0; i < res.length; i++) {
            res[i]=((double) dp[n][start])/((double)all);
            start++;
        }
        return res;
    }


    public String longestPalindrome(String s) {
        int len=s.length();
        if(len<=1)return s;
        int [][]dp=new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i]=1;
        }
        int start=0,end=0,max=0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if(s.charAt(i)==s.charAt(j)){
                    if(i==j-1)  dp[i][j]=1;
                    else dp[i][j]=dp[i+1][j-1];
                    if(dp[i][j]==1){
                        int temp=j-i+1;
                        if(temp>max){
                            start=i;end=j;max=temp;
                        }
                    }
                }
            }
        }
        return s.substring(start,end);
    }




    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node=new ListNode(0);
        node.next=head;
        ListNode p=node,q=head;
        while ((n--)>0){
            q=q.next;
        }
        while (q!=null){
            q=q.next;
            p=p.next;
        }
        p.next=p.next.next;
        return node.next;
    }



    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node=new ListNode(0    ),p=node;
        while (l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                p.next=l1;
                l1=l1.next;
            }else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }
        p.next=l1==null?l2:l1;
//        while (l1!=null){
//            p.next=l1;l1=l1.next;
//        }
//        while (l2!=null){
//            p.next=l2;l2=l2.next;
//        }
        return node.next;
    }


    public String reverseWords(String s) {
        StringBuilder sb=new StringBuilder(s.trim());
        reverse(sb,0,sb.length()-1);
        StringBuilder temp=new StringBuilder("");
        StringBuilder res=new StringBuilder("");
        int start=0;
        for (int i = 0; i < sb.length(); i++) {
            char c=sb.charAt(i);
            if(c==' '){
                if(temp.length()==0){
                    start=i+1;
                    continue;
                }
                temp.reverse();
                res.append(temp+" ");
                temp.delete(0,temp.length());
            }else{
                temp.append(c);
            }
        }
        temp.reverse();
        res.append(temp);
        return res.toString();
    }
    public void reverse(StringBuilder sb,int start,int end){
        int j=end;
        for (int i = start; i <=(end-start)/2+start; i++) {
            String temp=sb.charAt(i)+"";
            sb.replace(i,i+1,sb.charAt(j)+"");
            sb.replace(j,j+1,temp);
            j--;
        }
    }


    public int [][]direction=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
//    public boolean res=false;
//    public boolean exist(char[][] board, String word) {
//        int len1=board.length,len2=board[0].length;
//        for (int i = 0; i < len1; i++) {
//            for (int j = 0; j < len2; j++) {
//                int [][]flag=new int[len1][len2];
//                if(board[i][j]==word.charAt(0)){
//                    flag[i][j]=1;
//                    action(board,word,1,flag,i,j);
//                    flag[i][j]=0;
//                }
//                if(res)return true;
//            }
//        }
//        return false;
//    }
//    public void action(char[][] board, String word,int height,int [][]flag,int i,int j){
//        if(height==word.length()){
//            res=true;return;
//        }
//        for (int k = 0; k < 4; k++) {
//            int tempi=i+direction[k][0],tempj=j+direction[k][1];
//            if(!judge(board.length,board[0].length,tempi,tempj))continue;
//            System.out.println(tempi+"dd"+tempj);
//            char c=board[tempi][tempj];
//            if(c==word.charAt(height)&&flag[tempi][tempj]!=1&&!res){
//                flag[tempi][tempj]=1;
//                action(board,word,height+1,flag,tempi,tempj);
//                flag[tempi][tempj]=0;
//            }
//        }
//    }
//    public boolean judge(int len1,int len2,int i,int j){
//        if(i>=0&&j>=0&&i<len1&&j<len2)return true;
//        return false;
//    }



//    public int res=1;
//    public int movingCount(int m, int n, int k) {
//        int [][]board=new int[m][n];
//        board[0][0]=1;
//        action(board,k,0,0);
//        return res;
//    }
//    public void action(int [][]board,int k,int i,int j){
//        for (int l = 0; l < 4; l++) {
//            int tempi=i+direction[l][0],tempj=j+direction[l][1];
//            if(judge(tempi,tempj,board.length,board[0].length,k,board)){
//                System.out.println(tempi+"dd"+tempj);
//                board[tempi][tempj]=1;
//                res+=1;
//                action(board,k,tempi,tempj);
//                board[tempi][tempj]=0;
//            }
//        }
//    }
//    public boolean judge(int i,int j,int m,int n,int k,int [][]board){
//        int all=get(i)+get(j);
//        return (i>=0&&j>=0&&i<m&&j<n&&all<=k&&board[i][j]==0);
//    }
//    public int get(int i){
//        int all=0;
//        while (i>0){
//            all+=i%10;
//            i/=10;
//        }
//        return all;
//    }


//    public int[] exchange(int[] nums) {
//        int i=0,j=nums.length-1;
//        while (i<j){
//            while (i<j&&nums[i]%2==1)i++;
//            while (i<j&&nums[j]%2==0)j--;
//            if(i<j){
//                int temp=nums[i];
//                nums[i]=nums[j];
//                nums[j]=temp;
//            }
//        }
//        return nums;
//    }

//    public boolean result;
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//        if(A==null||B==null)return false;
//        action(A,B);
//        return result;
//    }
//    public void action(TreeNode root1, TreeNode root2){
//        if(root1.val==root2.val){
//            System.out.println(root1.val);
//            System.out.println(root1.left.val);
//            int res=action1(root1,root2);
//            if(res==1){
//                result=true;return;
//            }
//        }else{
//            if(root1.left!=null)action(root1.left,root2);
//            if(root1.right!=null)action(root1.right,root2);
//        }
//    }
//    public int action1(TreeNode root1, TreeNode root2){
//        if(root1.val==root2.val){
//            int res1=0,res2=0;
//            if(root1.left!=null&&root2.left!=null) res1=action1(root1.left,root2.left);
//            if(root1.left==null&&root2.left==null)res1=1;
//            if(root1.right!=null&&root2.right!=null) res2=action1(root1.right,root2.right);
//            if(root1.right==null&&root2.right==null)res2=1;
//            if(res1==1&&res2==1)return 1;
//            return 0;
//        }else return 0;
//    }



//    输入：s = "We are happy."
//    输出："We%20are%20happy."
//    public String replaceSpace(String s) {
//        StringBuilder sb=new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char c=s.charAt(i);
//            if(c!=' '){
//                sb.append(c);
//            }else {
//                sb.append("%20");
//            }
//        }
//        return sb.toString();
//    }


//    public int[] reversePrint(ListNode head) {
//        List<Integer>list=new ArrayList<>();
//        while (head!=null){
//            list.add(head.val);
//            head=head.next;
//        }
//        int[] a=new int[list.size()];
//        for (int i = 0; i < a.length; i++) {
//            a[i]=list.get(a.length-i-1);
//        }
//        return a;
//    }





//    public List<Integer>list=new ArrayList<>();
//    public int[] reversePrint(ListNode head) {
//        action(head);
//        int []a=new int[list.size()];
//        for (int i = 0; i < a.length; i++) {
//            a[i]=list.get(i);
//        }
//        return a;
//    }
//    public void action(ListNode head){
//        if(head!=null){
//            action(head.next);
//            list.add(head.val);
//        }
//    }


//    public int fib(int n) {
//        int []dp=new int[n+1];
//        if(n<=1)return n;
//        dp[1]=1;
//        for (int i = 2; i <=n; i++) {
//            dp[i]=(dp[i-1]+dp[i-2])%1000000007;
//        }
//        return dp[n];
//    }


//    public int cuttingRope(int n) {
//        if(n<=3)return n-1;
//        int i3=0,i2=0;
//        int res=0;
//        while (n>4){
//            n-=3;
//            res*=3;
//            res=res%1000000007;
//        }
//        return res*n%1000000007;
//    }


//    public int[] printNumbers(int n) {
//        int len=0;
//        while ((n--)>0){
//            len=len*10+9;
//        }
//        int[]a=new int[len];
//        for (int i = 0; i <len; i++) {
//            a[i]=i+1;
//        }
//        return a;
//    }

//    public int translateNum(int num) {
//        StringBuilder sb=new StringBuilder(String.valueOf(num));
//        int []dp=new int[sb.length()];
//        if(sb.length()<=1)return 1;
//        char c11=sb.charAt(0),c22=sb.charAt(1);
//        int res1=(int)(c11-'0')*10+(int)(c22-'0');
//        if(res1<=25) dp[1]=2;
//        else dp[1]=1;
//        dp[0]=1;
//        for (int i = 1; i < sb.length(); i++) {
//              char c1=sb.charAt(i-1),c2=sb.charAt(i);
//              int res=(int)(c1-'0')*10+(int)(c2-'0');
//              if(res<=25){
//                  dp[i]=dp[i-1]+dp[i-2];
//              }else {
//                  dp[i]=dp[i-1];
//              }
//        }
//        return dp[sb.length()-1];
//    }



//    public TreeNode mirrorTree(TreeNode root) {
//        if(root==null)return root;
//        action(root);
//        return root;
//    }
//    public void action(TreeNode root){
//        TreeNode r1=root.left,r2=root.right;
//        root.left=r2;
//        root.right=r1;
//        if(root.left!=null)action(root.left);
//        if(root.right!=null)action(root.right);
//    }





//    public boolean flag=true;
//    public boolean isSymmetric(TreeNode root) {
//        if(root==null)return flag;
//        if(root.left==null&&root.right==null)return flag;
//        else if(root.left!=null&&root.right!=null) {
//            action(root.left,root.right);
//            return flag;
//        }
//        else return false;
//    }
//    public void action(TreeNode roota,TreeNode rootb){
//        if(roota.val==rootb.val){
//            if(roota.left!=null&&rootb.right!=null)action(roota.left,rootb.right);
//            else if(roota.left==null&&rootb.right==null){}
//            else flag=false;
//
//            if(roota.right!=null&&rootb.left!=null)action(roota.right,rootb.left);
//            else if(roota.right==null&&rootb.left==null){}
//            else flag=false;
//        }else{
//            flag=false;
//        }
//    }





//    public int[] spiralOrder(int[][] matrix) {
//        if(matrix.length==0)return new int[]{};
//        int top=0,bottom=matrix.length-1,left=0,right=matrix[0].length-1;
//        List<Integer>list=new ArrayList<>();
//        while (true){
//            for (int i = left; i <=right ; i++) {
//                list.add(matrix[top][i]);
//            }
//            top++;
//            if(top>bottom)break;
//            for (int i = top; i <=bottom ; i++) {
//                list.add(matrix[right][i]);
//            }
//            right--;
//            if(right<left)break;
//            for (int i = right; i >=left; i++) {
//                list.add(matrix[bottom][i]);
//            }
//            bottom--;
//            if(bottom<top)break;
//            for (int i = bottom; i <=top ; i++) {
//                list.add(matrix[left][i]);
//            }
//            left++;
//            if(left>right)break;
//        }
//        return list.stream().mapToInt(Integer::intValue).toArray();
//    }



//    public boolean validateStackSequences(int[] pushed, int[] popped) {
//        if(pushed.length==0&&popped.length==0)return true;
//        else if(pushed.length==0||popped.length==0)return false;
//        Stack<Integer>stack=new Stack<>();
//        int counti=0,countj=0;
//        while (countj<popped.length){
//            if(counti>=pushed.length){
//                while (countj<popped.length){
//                    int val=stack.pop();
//                    if(val!=popped[countj])return false;
//                    countj++;
//                }
//                return true;
//            }
//            else if(pushed[counti]==popped[countj]){
//                counti++;countj++;
//            }else{
//                if(stack.isEmpty()){//如果一开始为空
//                    stack.push(pushed[counti]);
//                    counti++;
//                }else{
//                    int val=stack.peek();
//                    if(val==popped[countj]){
//                        stack.pop();
//                        countj++;
//                    }else {
//                        stack.push(pushed[counti]);
//                        counti++;
//                    }
//                }
//            }
//        }
//        return true;
//    }



//    public int[] levelOrder(TreeNode root) {
//        //Queue<Integer>que=new LinkedList<>();
//        ArrayList<TreeNode>queue=new ArrayList<>();
//        List<Integer>list=new ArrayList<>();
//        if(root==null)return new int[]{};
//        queue.add(0,root);
//        while (!queue.isEmpty()){
//            TreeNode node=queue.remove(0);
//            list.add(node.val);
//            if(node.left!=null)queue.add(queue.size(),node.left);
//            if(node.right!=null)queue.add(queue.size(),node.right);
//        }
//        return list.stream().mapToInt(Integer::intValue).toArray();
//    }

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>>lists=new ArrayList<>();
//        if(root==null)return lists;
//        ArrayList<TreeNode>queue=new ArrayList<>();
//        queue.add(0,root);
//        while (!queue.isEmpty()){
//            int size=queue.size();
//            List<Integer>list=new ArrayList<>();
//            while ((size--)>0){
//                TreeNode node=queue.remove(0);
//                list.add(node.val);
//                if(node.left!=null)queue.add(queue.size(),node.left);
//                if(node.right!=null)queue.add(queue.size(),node.right);
//            }
//            lists.add(list);
//        }
//        return lists;
//    }



    //反反得正------------
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>>lists=new ArrayList<>();
//        if(root==null)return lists;
//        ArrayList<TreeNode>queue=new ArrayList<>();
//        queue.add(0,root);
//        int i=0;
//        while (!queue.isEmpty()){
//            int size=queue.size();
//            List<Integer>list=new ArrayList<>();
//            i++;
//            while ((size--)>0){
//                if(i%2==1) {
//                    TreeNode node = queue.remove(size);
//                    list.add(node.val);
//                    if (node.left != null) queue.add(queue.size(), node.left);
//                    if (node.right != null) queue.add(queue.size(), node.right);
//                }else{
//                    TreeNode node = queue.remove(size);
//                    list.add(node.val);
//                    if (node.right != null) queue.add(queue.size(), node.right);
//                    if (node.left != null) queue.add(queue.size(), node.left);
//                }
//            }
//            lists.add(list);
//        }
//        return lists;
//    }



//    public int cuttingRope(int n) {
//        BigInteger []dp=new BigInteger[n+1];
//        if(n<=1)return n;
//        dp[1]=BigInteger.valueOf(1);
//        dp[2]=BigInteger.valueOf(1);
//        for (int time = 2; time <=n; time++) {
//            for (int i = 1; i <=time-1 ; i++) {
//                int j=time-i;
//                dp[time]=dp[time].max(dp[j].multiply(BigInteger.valueOf(i)).max(BigInteger.valueOf(i*j)));
//            }
//        }
//        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
//    }



//    public int translateNum(int num) {
//        String s=String.valueOf(num);
//        if(s.length()<=1)return 1;
//        int a=1,b=1,c=0;
//        for (int i = 1; i <s.length(); i++) {
//            String temp=s.substring(i-1,i+1);
//            if(temp.compareTo("10")>=0&&temp.compareTo("25")<=0){
//                c=a+b;
//            }else c=b;
//            a=b;b=c;
//        }
//        return c;
//    }


//    public Node copyRandomList(Node head) {
//        if(head==null)return null;
//
//    }

//    public int majorityElement(int[] nums) {
//        int len=nums.length;
//        int votes=0,x=nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            if(votes==0)x=nums[i];
//            votes=x==nums[i]?votes+1:votes-1;
//        }
//        return x;
//    }



//    public int[] getLeastNumbers(int[] arr, int k) {
//
//    }
//    public void action(int[] a,int k){
//
//    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        //迭代版本
////        while (root!=null){
////            if(root.val<p.val&&root.val<q.val){//都在右边
////                root=root.right;
////            }else if(root.val>p.val&&root.val>q.val){
////                root=root.left;
////            }else {
////                return root;
////            }
////        }
////        return root;
//        return action(root,p,q);
//
//    }
//    public TreeNode action(TreeNode root, TreeNode p, TreeNode q){
//        if(root==null)return null;
//        if(root.val<p.val&&root.val<q.val){
//            return action(root.right,p,q);
//        }else if(root.val>p.val&&root.val>q.val) {
//            return action(root.left,p,q);
//        }else{
//            return root;
//        }
//    }



//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if(root==null)return null;
//        if(root==p||root==q)return root;
//        TreeNode left=lowestCommonAncestor(root.left,p,q);
//        TreeNode right=lowestCommonAncestor(root.right,p,q);
//        if(left!=null&&right!=null)return root;
//        if(left!=null)return left;
//        if(right!=null)return right;
//        return null;
//    }

//    public int search(int[] nums, int target) {
//        if(nums.length==0)return 0;
//        int left=0,right=nums.length;
//        while (left<right){//找到第一个大于target的
//            int mid=left+(right-left)/2;
//            if(nums[mid]>target){
//                right=mid;
//            }else left=mid+1;
//        }
//        int tempi=left-1;
//        //if(tempi<0||nums[tempi]!=target)return 0;//如果没有target
//        left=0;right=nums.length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]>target)right=mid;
//            else if(nums[mid]<target)left=mid+1;
//            else right=mid;
//        }
//        //System.out.println(tempi+"dd"+left);
//        return tempi-left+1;
//    }

//    public int[] searchRange(int[] nums, int target) {
//        if(nums.length==0)return new int[]{-1,-1};
//        int left=0,right=nums.length;
//        while (left<right){//找到第一个大于target的
//            int mid=left+(right-left)/2;
//            if(nums[mid]>target){
//                right=mid;
//            }else left=mid+1;
//        }
//        int tempi=left-1;
//        if(tempi<0||nums[tempi]!=target)return new int[]{-1,-1};//如果没有target
//        left=0;right=nums.length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]>target)right=mid;
//            else if(nums[mid]<target)left=mid+1;
//            else right=mid;
//        }
//        //System.out.println(tempi+"dd"+left);
//        return new int[]{left,tempi};
//    }

//    public int[] twoSum(int[] nums, int target) {
////        if(nums.length==1)return
//        int i=0,j=nums.length-1;
//        while (i<j){
//            int all=nums[i]+nums[j];
//            if(all<target){
//                i++;
//            }else if(all>target){
//                j--;
//            }else {
//                return new int[]{i,j};
//            }
//        }
//        return new int[]{-1,-1};
//    }

    public int[][] findContinuousSequence(int target) {
        if(target<3)return new int[][]{};
        List<int[]>list=new ArrayList<>();
        int i=1,j=2;
        int all=1;
        while (i<j){
            if(all<target){
                j++;all+=j;
            }else if(all>target){
                i++;all-=i;
            }else {
                int []a=new int[j-i+1];
                for (int k = 0; k < a.length; k++) {
                    a[k]=i+k;
                }
                list.add(a);
                i++;j++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

//    class Node {
//        int val;
//        Node next;
//        Node random;
//
//        public Node(int val) {
//            this.val = val;
//            this.next = null;
//            this.random = null;
//        }
//    }
//    public Node copyRandomList(Node head) {
//        HashMap<Node,Node>map=new HashMap();
//        Node temphead=head,temp=null;
//        int time=1;
//        while (head!=null){
//            Node node=new Node(head.val);
//            map.put(head,node);
//            head=head.next;
//            if(time==1){
//                temp=node;time++;
//            }
//        }
//        while (temphead!=null){
//            map.get(temphead).next=map.get(temphead.next);
//            map.get(temphead).random=map.get(temphead.random);
//        }
//        return temp;
//    }








//    class Node {
//        public int val;
//        public Node left;
//        public Node right;
//
//        public Node() {}
//
//        public Node(int _val) {
//            val = _val;
//        }
//
//        public Node(int _val,Node _left,Node _right) {
//            val = _val;
//            left = _left;
//            right = _right;
//        }
//    };
//    public Node head=null,tail=null;
//    public Node treeToDoublyList(Node root) {
//        if(root==null)return null;
//        action(root);
//        head.left=tail;
//        tail.right=head;
//        return head;
//    }
//    public void action(Node root){
//        if(root.left!=null)action(root.left);
//        if(head==null)head=root;
//        if(tail!=null){
//            tail.right=root;
//            root.left=tail;
//        }
//        tail=root;
//
//        if(root.right!=null)action(root.right);
//    }


    ///-----------------  以往都是采用nums[mid]和nums[mid+1]等来比较，这次采用的是 mid 和 right比较
//    public int minArray(int[] numbers) {
//        int left=0,right=numbers.length;
//        if(numbers.length==1)return numbers[0];
//        while (left<right){
//            int mid=left+(right-left)/2;
//            //拿mid 和right 比较
//            if(numbers[mid]==numbers[right]){
//                right--;
//            }else if(numbers[mid]<numbers[right]){
//                right=mid;
//            }else {
//                left=mid+1;
//            }
//
//        }
//        return numbers[left];
//    }

//    public boolean isStraight(int[] nums) {
//        HashSet<Integer>set=new HashSet<>();
//        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
//        for (int i = 0; i < nums.length; i++) {
//            int a=nums[i];
//            if(a==0)continue;
//            if(set.contains(a))return false;
//            set.add(a);
//            max=Math.max(max,a);
//            min=Math.min(min,a);
//        }
//        return max-min<5?true:false;
//    }
//



//    public int max=0;
//    public int maxDepth(TreeNode root) {
//        if(root==null)return max;
//        action(root,1);
//        return max;
//    }
//    public void action(TreeNode root,int height){
//        max=Math.max(max,height);
//        if(root.left!=null)action(root.left,height+1);
//        if(root.right!=null)action(root.right,height+1);
//    }



//    public boolean flag=true;
//    public boolean isBalanced(TreeNode root) {
//        if(root==null)return true;
//        action(root);
//        return flag;
//    }
//    public int action(TreeNode root){
//        int left=0,right=0;
//        if(root.left!=null)left=action(root.left);
//        if(root.right!=null)right=action(root.right);
//        if(Math.abs(left-right)>1)flag=false;
//        return Math.max(left,right)+1;
//    }



//    public String reverseLeftWords(String s, int n) {
//        //切片函数，如果不允许切片的话
////        return s.substring(n,s.length())+s.substring(0,n);
//        StringBuilder sb=new StringBuilder();
//        for (int i = n; i < s.length(); i++) {
//            sb.append(s.charAt(i));
//        }
//        for (int i = 0; i < n; i++) {
//            sb.append(s.charAt(i));
//        }
//        return sb.toString();
//    }


//    public int[] maxSlidingWindow(int[] nums, int k) {
//        ArrayList<Integer>come=new ArrayList<>();
//        ArrayList<Integer>max=new ArrayList<>();
//        ArrayList<Integer>res=new ArrayList<>();
//        for (int i = 0; i < k; i++) {
//            come.add(nums[i]);
//            while (!max.isEmpty()&&nums[i]>max.get(max.size()-1)){
//                max.remove(max.size()-1);
//            }
//            max.add(nums[i]);
//        }
//        res.add(max.get(0));
//        for (int i = k; i <nums.length   ; i++) {
//            int left=come.remove(0),right=nums[i];
//            come.add(right);
//            if(left==max.get(0)){
//                max.remove(0);
//            }
//            while (!max.isEmpty()&&nums[i]>max.get(max.size()-1)){
//                max.remove(max.size()-1);
//            }
//            max.add(right);
//            res.add(max.get(0)) ;
//        }
//        return res.stream().mapToInt(Integer::intValue).toArray();
//    }



//    public int[][] merge(int[][] intervals) {
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0]==o2[0])return 0;
//                else return o1[0]<o2[0]?-1:1;
//            }
//        });
//        ArrayList<int[]>list=new ArrayList<>();
//        for (int i = 0; i < intervals.length-1; i++) {
//            if(intervals[i][1]>=intervals[i+1][0]){
//                //int []a=new int[]{intervals[i][0],intervals[i+1][1]};
//                //list.add(a);
//                intervals[i+1][0]=intervals[i][0];
//                intervals[i+1][1]=Math.max(intervals[i+1][1],intervals[i][1]);
//            }else{
//                int []a=new int[]{intervals[i][0],intervals[i][1]};
//                list.add(a);
//            }
//        }
//        list.add(new int[]{intervals[intervals.length-1][0],intervals[intervals.length-1][1]});
//        return list.toArray(new int[list.size()][]);
//    }


//    public int minDistance(String word1, String word2) {
//        int len1=word1.length(),len2=word2.length();
//        int [][]dp=new int[len1][len2];
//        for (int i = 0; i < len1; i++) {
//            dp[i][0]=i;
//        }
//        for (int i = 0; i < len2; i++) {
//            dp[0][i]=i;
//        }
//        for (int i = 1; i < len1; i++) {
//            for (int j = 1; j < len2; j++) {
//                if(word1.charAt(i)==word2.charAt(j)){
//                    dp[i][j]=dp[i-1][j-1];
//                }else {
//                    dp[i][j]=Math.max(dp[i-1][j-1]+1,Math.max(dp[i-1][j]+1,dp[i][j-1]+1));
//                }
//            }
//        }
//        return dp[len1-1][len2-1];
//    }



//    public void setZeroes(int[][] matrix) {
//        //题目要求常数空间，一般来说都是在提供给我们的空间上动手脚， 传入matrix【】【】  那就在matrix 上做记号
//        //用matrix 的第一行第一列来记录是否全部设为0，
//        // 1 1 2 0 1 1
//        // 0 1 1 0 1 1
//        // 1 1 1 1 1 1
//        // 那就是第二行 第四列 全部设为0
//        int flag_hang=0,flag_lie=0;
//        for (int i = 1; i < matrix.length; i++) {
//            if(matrix[i][0]==0)flag_lie=1;
//        }
//        for (int i = 1; i < matrix[0].length; i++) {
//            if(matrix[0][i]==0)flag_hang=1;
//        }
//        if(matrix[0][0]==0){
//            flag_hang=1;flag_lie=1;
//        }
//        for (int i = 1; i < matrix.length; i++) {
//            for (int j = 1; j < matrix[0].length; j++) {
//                if(matrix[i][j]==0){
//                    matrix[0][j]=0;
//                    matrix[i][0]=0;
//                }
//            }
//        }
//        for (int i = 0; i <matrix.length; i++) {
//            if(matrix[i][0]==0){
//                for (int j = 0; j < matrix[0].length; j++) {
//                    matrix[i][j]=0;
//                }
//            }
//        }
//        for (int i = 0; i < matrix[0].length; i++) {
//            if(matrix[0][i]==0){
//                for (int j = 0; j <matrix.length ; j++) {
//                    matrix[j][i]=0;
//                }
//            }
//        }
//        if(flag_hang==1){
//            for (int i = 0; i <matrix[0].length; i++) {
//                matrix[0][i]=0;
//            }
//        }
//        if(flag_lie==1){
//            for (int i = 0; i < matrix.length; i++) {
//                matrix[i][0]=0;
//            }
//        }
//
//    }



//    public boolean searchMatrix(int[][] matrix, int target) {
//        if(matrix.length==0)return false;
//        if(matrix[0].length==0)return false;
//        if(matrix[0].length==1)return matrix[0][0]==target?true:false;
//        int left=0,right=matrix.length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(matrix[mid][0]<target){
//                left=mid+1;
//            }else if(matrix[mid][0]==target){
//                return true;
//            }else {
//                right=mid;
//            }
//        }
//        int temp=left-1;
//        left=0;right=matrix[0].length;
//        while (left<right){
//            int mid=left+(right-left)/2;
//            if(matrix[temp][mid]==target)return true;
//            else if(matrix[temp][mid]<target){
//                left=mid+1;
//            }else {
//                right=mid-1;
//            }
//        }
//        return false;
//    }




//    public void sortColors(int[] nums) {
//        int i=0,j=nums.length-1,k=0;
//        if(nums.length<=1)return;
//        while (k<j){
//            if(nums[k]==0){
//                int temp=nums[i];
//                nums[i]=nums[k];
//                nums[k]=temp;
//                k++;i++;
//            }else if(nums[k]==1){
//                k++;
//            }else {
//                int temp=nums[i];
//                nums[i]=nums[j];
//                nums[j]=temp;
//                j--;
//            }
//        }
//
//    }


    public String minWindow(String s, String t) {
        HashMap<Character, Integer>map=new HashMap<>();
        ArrayList<Character>que=new ArrayList<>();
        int count=0;
        for (int i = 0; i < t.length(); i++) {
            char c=t.charAt(i);
            if(map.containsKey(c))map.put(c,map.get(c)+1);
            else map.put(c,1);
            count++;
        }
        int left=0,right=0,min=Integer.MAX_VALUE,start=0,end=0;
        for (int i = 0; i <s.length(); i++) {
            char c=s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                if(map.get(c)>=0)
                count--;
            }
            while (count==0){
                System.out.println("11");
                char temp=s.charAt(left);
                if(map.containsKey(temp)){
                    map.put(temp,map.get(temp)+1);
                    if(map.get(c)>0)count++;
                }else {
                    left++;
                    if(min>right-left+1){
                        min=right-left+1;
                        start=left;
                        end=right;
                    }
                }
            }
        }
        return end==0?"":s.substring(start,end+1);
    }
    public void action(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int x=sc.nextInt(),a=sc.nextInt(),y=sc.nextInt(),b=sc.nextInt();
            if(x/a>y/b) System.out.println(">");
            else if(x/a==y/b) System.out.println("=");
            else System.out.println("<");
        }
    }

//    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        try {
//            while (sc.hasNext()) {
//                BigInteger x=sc.nextBigInteger(), a=sc.nextBigInteger(), y=sc.nextBigInteger(), b=sc.nextBigInteger();
//                if(x.multiply(b).compareTo(y.multiply(a))>0) System.out.println(">");
//                else if(x.multiply(b).compareTo(y.multiply(a))==0) System.out.println("<");
//                else System.out.println("=");
//            }
//        }catch (InputMismatchException e){
//        }
//
//    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        try {
            String s=sc.nextLine();
            if(s.length()==0){
                System.out.println(0);
                return;
            }
            int count=0;
            count=s.charAt(0)=='w'?1:0;
            for (int i = 1; i < s.length(); i++) {
                char c=s.charAt(i);
                if(c=='w'&&s.charAt(i-1)=='w'){
                    count+=2;
                }else if(c=='w'&&s.charAt(i-1)!='w'){
                    count+=1;
                }else {

                }
            }
            System.out.println(count);
        }catch (InputMismatchException e){
        }

    }
    public static  int get(int []a,int end) {
        int min=Integer.MAX_VALUE,time=0;
        for (int i = 0; i <end; i++) {
            if(min>a[i]) {
                min=a[i];
                time=i;
            }

        }
        return time;
    }






}
class myqueue<T>{
    private Stack<T>stack1;
    private Stack<T>stack2;
    myqueue(){
        stack1=new Stack<>();
        stack2=new Stack<>();
    }
    public void push(T a){
        stack1.push(a);
    }
    public T pop() throws Exception{
        if(isEmpty())throw new Exception();
        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){
                T temp=stack1.pop();
                stack2.push(temp);
            }
            return stack2.pop();
        }else{
            return stack2.pop();
        }
    }
    public boolean isEmpty(){
        if(stack1.isEmpty()&&stack2.isEmpty())return true;
        else return false;
    }

}

class mystack<T>{
    private LinkedList<T>list1;
    private LinkedList<T>list2;
    mystack(){
        list1=new LinkedList<>();
        list2=new LinkedList<>();
    }
    public void push(T a){
        if(isEmpty()){
            list1.add(a);
            return;
        }
        if(list1.isEmpty()){
            list2.add(a);
        }else {
            list1.add(a);
        }
    }
    public boolean isEmpty(){
        if(list1.isEmpty()&&list2.isEmpty())return true;
        else return false;
    }
    public T pop() throws Exception{
        if(isEmpty())throw new Exception();
        LinkedList<T>listtemp1=list1.isEmpty()?list2:list1;
        LinkedList<T>listtemp2=list1.isEmpty()?list1:list2;
        int size=listtemp1.size()-1;
        for (;size>0;size--){
            T temp=listtemp1.remove();
            listtemp2.add(temp);
        }
        T temp=listtemp1.remove();
        return temp;
    }

}

class MinStack {
    /** initialize your data structure here. */
    Stack<Integer>data;
    Stack<Integer>helper;
    public MinStack() {
        data=new Stack<>();
        helper=new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(helper.isEmpty()){
            helper.push(x);
        }else {
            int temp=helper.peek();
            if(temp>=x){
                helper.push(x);
            }
        }
    }

    public void pop() {
        int temp=data.pop();
        if(temp==helper.peek()) helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return helper.peek();
    }
}

class MaxQueue {

    Queue<Integer>data;
    Deque<Integer>helper;
    public MaxQueue() {
        data=new LinkedList<>();
        helper=new ArrayDeque<>();
    }

    public int max_value() {
        if(helper.isEmpty())return -1;
        return helper.getFirst();
    }

    public void push_back(int value) {
        data.add(value);
        if(helper.isEmpty()){
            helper.addFirst(value);
        }else {
            while (!helper.isEmpty()&&helper.getLast()<value){
                helper.removeLast();
            }
            helper.addLast(value);
        }
    }

    public int pop_front() {
        if(data.isEmpty())return -1;
        int value=data.remove();
        if(value==helper.getFirst()){
            helper.removeFirst();
        }
        return value;
    }
}

class CQueue {

    public Stack<Integer>stack1;
    public Stack<Integer>stack2;
    public CQueue() {
        stack1=new Stack<>();
        stack2=new Stack<>();
    }
    // 1 5 3 4     1 5 3
    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack2.isEmpty()&&stack1.isEmpty())return -1;
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else {
            while (stack1.size()>1){
                int val=stack1.pop();
                stack2.push(val);
            }
            return stack1.pop();
        }
    }
}





class test implements Runnable{
    static test instance1=new test();
    static test instance2=new test();
    public Object object=new Object();
    @Override
    public void run() {
        method();
    }
    public  void method(){
        synchronized (object){
            try {
                System.out.println("tttt");
                Thread.sleep(10000);
                System.out.println("fff");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Thread a=new Thread(instance1);
        Thread b=new Thread(instance2);
        a.start();b.start();
        Class c=b.getClass();
        c.toString();
    }
}

class abcd123 {
    private static volatile int x=1;
    private static Semaphore actiona=new Semaphore(1);
    private static Semaphore actionb=new Semaphore(0);
    private static Semaphore actionc=new Semaphore(0);
    private static Semaphore actiond=new Semaphore(0);

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(10);



    }
}



class UnionFind {
    private int[] parent;
    private int size;

    public UnionFind(int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);
        if (firstRoot == secondRoot) {
            return;
        }
        parent[firstRoot] = secondRoot;
    }

    /**
     * 本并查集使用数组实现，为了更直观地看清内部数据，采用打印数组
     */
    private void printArr() {
        for (int parent : this.parent) {
            System.out.print(parent + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
    }
//    public static int [][]direction={{0,1},{1,0},{0,-1},{-1,0}};
//    static class node{
//        int i,j;
//    }
//    public static int n,m;
//    public static int [][]flag;
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        try {
//            n=sc.nextInt();m=sc.nextInt();
//            int [][]matrix=new int[n][m];
//            flag=new int[n][m];
//            for (int i = 0; i < n; i++) {
//                Reader reader=new InputStreamReader(System.in);
//                BufferedReader reader1=new BufferedReader(reader);
////                String s1=sc.nextLine();
//                String s=sc.next();
//
//                for (int j = 0; j < m; j++) {
//                    char c=s.charAt(j);
//                    if(c=='W'){
//                        matrix[i][j]=1;
//                    }else if(c=='S'){
//                        matrix[i][j]=2;
//                    }else if(c=='A'){
//                        matrix[i][j]=3;
//                    }else {
//                        matrix[i][j]=4;
//                    }
//                }
//            }
//            ArrayList <node>list=new ArrayList<>();
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if(flag[i][j]==0)
//                        action(matrix,list,i,j);
//                }
//            }
//            int count=0;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if(flag[i][j]==200)count+=1;
//                }
//            }
//            System.out.println(count);
//
//        }catch (InputMismatchException i ){
//        }
//    }
//    public static int judge(int ii,int jj){
//        if(ii>=0&&ii<n&&jj>=0&&jj<m){
//            if(flag[ii][jj]==0){
//                return 1;
//            }
//            else if(flag[ii][jj]==200){//出界了
//                return 0;
//            }
//            else {
//                return 2;//
//            }
//        }
//        return 0;
//    }
//
//    public static void action(int [][]matrix,ArrayList <node>list,int ii,int jj){
//        int tempi,tempj;// w s a d {{0,1},{1,0},{0,-1},{-1,0}};
//        if(matrix[ii][jj]==1){
//            tempi=ii-1;tempj=jj;
//        }else if(matrix[ii][jj]==2){
//            tempi=ii+1;tempj=jj;
//        }else if(matrix[ii][jj]==3){
//            tempi=ii;tempj=jj-1;
//        }else {
//            tempi=ii;tempj=jj+1;
//        }
//        // System.out.println(tempi+"dd"+tempj);
//        node no=new node();
//        no.i=ii;no.j=jj;
//        flag[ii][jj]=1;
//        list.add(no);
//        if(judge(tempi,tempj)==1){//正常
//            action(matrix,list,tempi,tempj);
//        }else if(judge(tempi,tempj)==2){// 循环了
//            for (node node : list) {
//                flag[node.i][node.j]=100;
//            }
//            list.clear();
//            return;
//        }else {//出界了
//            for (node node : list) {
//                flag[node.i][node.j]=200;
//            }
//            list.clear();
//            return;
//        }
//
//    }
}


class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int time=sc.nextInt();
            while ((time--)>0){
                int n=sc.nextInt(),m=sc.nextInt(),a=sc.nextInt(),b=sc.nextInt();
                int [][]matrix=new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        matrix[i][j]=sc.nextInt();
                    }
                }
                int flag=1;
                for (int i = 0; i < n&&flag==1; i++) {
                    for (int j = 0; j < m&&flag==1; j++) {
                        int xia=i+a-1,you=j+b-1;
                        if(xia>=n||you>=m)continue;;
                        if(setandjudge(matrix,i,j,xia,you,matrix[i][j])==1){

                        }else {//不符合
                            flag=2;
                            break;
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if(matrix[i][j]>0)flag=2;
                    }
                }
                if(flag==2) System.out.println("QAQ");
                else System.out.println("^_^");

            }

        }catch (InputMismatchException i ){
        }
    }
    public static int setandjudge(int [][]matrix,int i,int j,int xia,int you,int value){
        for (int k = i; k <=xia ; k++) {
            for (int l = j; l <=you ; l++) {
                matrix[k][l]-=value;
                if(matrix[k][l]<0)return 0;
            }
        }
        return 1;
    }
}