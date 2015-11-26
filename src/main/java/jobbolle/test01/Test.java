package jobbolle.test01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
 
/**
 * http://blog.jobbole.com/41204/
 * 伯乐在线编程挑战第 0 期 – 呼叫转移系统
 * 
 * 测试单向连接
 * 4
 * 0 1 2 3
 * 4 5 1 5
 * 3 4 2 1
 * 1 2 1 4
 * 2
 * [0, 1, 2][3, 4, 5]
 * 
 * 测试双向连接
 * 3
 * 0 1 2 3
 * 2 3 1 5
 * 1 2 2 1
 * 2
 * [0,1,2,3]
 * 
 * 测试连接后续处理
 * 6
 * 0 1 2 3
 * 2 3 1 5
 * 10 1 2 2
 * 1 2 2 5
 * 11 10 2 1
 * 12 0 2 4
 * 2
 * [12, 0, 1, 2, 3][11, 10, 1, 2, 3]
 */
public class Test {
 
    public static void main(String args[]) throws Exception{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buf.readLine());
        HashMap<Integer,DayData> map = new HashMap<Integer,DayData>();
        for(int i=0;i<n;){
            String[] str = buf.readLine().split(" ");
            String from = str[0];
            String to = str[1];
            int start = Integer.parseInt(str[2]);
            int duration = Integer.parseInt(str[3]);
            String errorMsg = "";
            for(int j=0;j<duration;j++){
                if(map.containsKey(start+j)){
                    DayData dayData = map.get(start+j);
                    errorMsg = dayData.checkValid(from, to);
                    if(!"".equals(errorMsg)){
                        System.out.println(errorMsg+"（第"+(start+j)+"天）");
                        System.out.println("请重新输入第"+(i+1)+"条数据：");
                        break;
                    }
                }
            }
            if("".equals(errorMsg)){
                i++;
            }
            else{
                continue;
            }
            for(int j=0;j<duration;j++){
                if(map.containsKey(start+j)){
                    DayData dayData = map.get(start+j);
                    dayData.add(from, to);
                }
                else{
                    DayData dayData = new DayData();
                    dayData.add(from, to);
                    map.put(start+j,dayData);
                }
            }
        }
        System.out.println("输入查询日期：");
        String str;
        while((str = buf.readLine())!=null){
            int day = Integer.parseInt(str);
            if(map.containsKey(day)){
                DayData dayData = map.get(day);
                System.out.println("第 "+day+" 天共有 "+dayData.getSize()+" 个呼叫转移设置");
                System.out.println("第 "+day+" 天最长的呼叫转移是 "+dayData.getDepth()+" 次 "+dayData.getDepthListStringValue());
            }
            else{
                System.out.println("第 "+day+" 天共有 0 个呼叫转移设置");
                System.out.println("第 "+day+" 天最长的呼叫转移是 0 次");
            }
            System.out.println("输入查询日期：");
        }
    }
     
    private static class DayData{
        private List<String> fromList = new ArrayList<String>();
        private List<LinkedList<String>> depthList = new ArrayList<LinkedList<String>>();
         
        public String checkValid(String from, String to){
            if(fromList.contains(from)){
                return "输入的被叫人号码"+from+"已存在";
            }
            for(int i=0;i<depthList.size();i++){
                LinkedList<String> list = depthList.get(i);
                if(list.contains(from) && list.contains(to)){
                    return "输入的呼叫转移号码出现环路";
                }
            }
            return "";
        }
         
        public void add(String from, String to){
            Boolean flag = false;
            int index = -1;
            for(int i=0;i<depthList.size();i++){
                LinkedList<String> list = depthList.get(i);
                if(list.getFirst().equals(to)){
                    flag = true;
                    index = i;
                    break;
                }
            }
            if(flag){
                Boolean topBottomFlag = false;
                for(int i=0;i<depthList.size();i++){
                    LinkedList<String> list = depthList.get(i);
                    if(list.getLast().equals(from)){
                        list.addAll(depthList.get(index));
                        topBottomFlag = true;
                    }
                }
                if(topBottomFlag){
                    depthList.remove(index);
                }
                else{
                    depthList.get(index).addFirst(from);
                }
            }
            else{
                for(int i=0;i<depthList.size();i++){
                    LinkedList<String> list = depthList.get(i);
                    if(list.getLast().equals(from)){
                        list.add(to);
                        flag = true;
                    }
                }
                if(!flag){
                    LinkedList<String> newList = new LinkedList<String>();
                    newList.add(from);
                    newList.add(to);
                    depthList.add(newList);
                }
            }       
            fromList.add(from);
        }
         
        public int getSize(){
            return fromList.size();
        }
         
        public int getDepth(){
            int maxDepth = 1;
            for(int i=0;i<depthList.size();i++){
                LinkedList<String> list = depthList.get(i);
                if(list.size()-1>maxDepth)
                    maxDepth = list.size()-1;
            }
            return maxDepth;
        }
         
        public String getDepthListStringValue(){
            int maxDepth = getDepth();
            StringBuilder msg = new StringBuilder();
            for(int i=0;i<depthList.size();i++){
                LinkedList<String> list = depthList.get(i);
                if(list.size()-1==maxDepth)
                    msg.append(Arrays.toString(list.toArray()));
            }
            return msg.toString();
        }
    }
}
/*
我的代码包含了将输入数据保存并能够快速查询的数据结构，并对输入数据进行了验证
HashMap，key为第几天，value为特殊类DayData
DayData类包括一个呼叫号码的列表ArrayList和一个保存转移链的列表ArrayList>
1、呼叫号码列表ArrayList
用于判断第N天的呼叫号码是否重复，如果重复则提示”不能同时从一个号码转移到多个号码“
列表的长度即当天设置的呼叫转移数
 可以将列表改为HashMap，key为呼叫号码，value为呼叫号码+转移号码，这样就能保存所有信息
2、保存转移链的列表ArrayList>
保存内容类似[[1,2],[3,4],[5,6]]，表示[1->2],[3->4],[5->6]
如果加入[4,5]，保存内容变为[[1,2],[3,4,5,6]]
验证转移不能出现环路，只要验证输入的呼叫号码和转移号码不同时在一个链表中，即不同时出现在[1,2]或[3,4,5,6]中
 由于不会出现[[1,2],[1,3]]的形式（呼叫号码重复），所以在加入数据时，转移号码最多匹配到一个链表的头部
 所以先匹配转移号码，再匹配呼叫号码
 例如[[1,3],[2,3],[4,5]]，加入数据[3,4]，先处理转移号码，变为[[1,3],[2,3],[3,4,5]]，再处理呼叫号码，变为[[1,3,4,5],[2,3,4,5],[3,4,5]]
如果转移号码和呼叫号码都匹配成功，则需要删除转移号码匹配到的记录，变为[[1,3,4,5],[2,3,4,5]]
使用链表是因为方便在头部插入addFirst和查找getFirst
最终转移深度就是最长的LinkedList的长度
 使用这种数据结构保存了转移链的数据，所以在输出深度时还能输出转移链的详细内容
 如果在输入完数据后，将每天的最长的LinkedList的长度计算后保存，则在查询时的复杂度为O(1)，仅需要从hashmap中取得当天的DayData类即可得到数量和深度
 缺点是在天数范围很大时，在内存使用量和插入数据时的操作数会变得很大
*/