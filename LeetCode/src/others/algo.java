package others;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 算法
 *
 * @author 97557
 * @date 2023/07/05
 */
public class algo {
    /**
     * 2600. K 件物品的最大和
     *
     * @param numOnes    全国矿工工会
     * @param numZeros   num 0
     * @param numNegOnes num底片
     * @param k          k
     * @return int
     */
    public static int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        } else if (k <= numOnes + numZeros) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }

    /**
     * 2413. 最小偶倍数
     *
     * @param n n
     * @return int
     */
    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        } else {
            return n * 2;
        }
    }

    /**
     * 2404. 出现最频繁的偶数元素
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public static int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        if (map.size() == 0) {
            return -1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Integer max = Collections.max(map.values());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(max)) {
                list.add(entry.getKey());
            }
        }
        list.sort(Comparator.naturalOrder());
        return list.get(0);
    }

    /**
     * 2418. 按身高排序
     *
     * @param names   名字
     * @param heights 高度
     */
    public static String[] sortPeople(String[] names, int[] heights) {
        HashMap<Integer, String> map = new HashMap<>();
        int size = heights.length;
        String[] newNames = new String[size];
        for (int i = 0; i < size; i++) {
            map.put(heights[i], names[i]);
        }
        Arrays.sort(heights);
        for (int i = 0; i < size; i++) {
            newNames[i] = map.get(heights[size - 1 - i]);
        }
        return newNames;
    }

    /**
     * 66. 加一
     *
     * @param digits 数字
     * @return {@link int[]}
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    /**
     * 9. 回文数
     *
     * @param x x
     * @return boolean
     */
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        String s = String.valueOf(x);
        String[] split = s.split("");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            if (!split[i].equals(split[length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public static int removeDuplicates(int[] nums) {
        Integer[] array = Arrays.stream(nums).distinct().boxed().toArray(Integer[]::new);
        for (int i = 0; i < array.length; i++) {
            nums[i] = array[i];
        }
        return array.length;
    }

    /**
     * 1003. 检查替换后的词是否有效(用栈效率更高)
     *
     * @param s
     * @return boolean
     */
    public static boolean isValid(String s) {
        String tmp = s;
        String tmp1 = "";
        for (int i = 0; i < s.length() / 3; i++) {
            tmp = tmp.replace("abc", "");
        }
        return tmp1.equals(tmp);
    }

    /**
     * 2432. 处理用时最长的那个任务的员工
     *
     * @param n    n
     * @param logs 日志
     * @return int
     */
    public static int hardestWorker(int n, int[][] logs) {
        if (logs.length == 1) {
            return logs[0][0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        //拿到第一个开始时间
        int tmp = logs[0][1];
        map.put(logs[0][0], logs[0][1]);
        for (int i = 0; i < logs.length - 1; i++) {
            int j = i + 1;
            if ((logs[j][1] - logs[i][1]) >= tmp) {
                tmp = logs[j][1] - logs[i][1];
                map.put(logs[j][0], tmp);
            }
        }
        int worker = n;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(tmp) && entry.getKey() < worker) {
                worker = entry.getKey();
            }
        }
        return worker;
    }

    /**
     * 412. Fizz Buzz
     *
     * @param n n
     * @return {@link List}<{@link String}>
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    /**
     * 1480. 一维数组的动态和
     *
     * @param nums 全国矿工工会
     * @return {@link int[]}
     */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }

    /**
     * 1342. 将数字变成 0 的操作次数
     *
     * @param num 全国矿工工会
     * @return int
     */
    public int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
                count++;
            } else {
                num--;
                count++;
            }
        }
        return count;
    }

    /**
     * 1672. 最富有客户的资产总量
     *
     * @param accounts 账户
     * @return int
     */
    public int maximumWealth(int[][] accounts) {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            int tmp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                tmp += accounts[i][j];
            }
            if (tmp > sum) {
                sum = tmp;
            }
        }
        return sum;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote 赎金注意
     * @param magazine   杂志
     * @return boolean
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        boolean flag = true;
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            map.put(ransomNote.charAt(i), 1 + map.getOrDefault(ransomNote.charAt(i), 0));
        }
        for (int i = 0; i < magazine.length(); i++) {
            map1.put(magazine.charAt(i), 1 + map1.getOrDefault(magazine.charAt(i), 0));
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (map1.get(entry.getKey()) == null) {
                flag = false;
                break;
            } else if (map1.get(entry.getKey()) < (entry.getValue())) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
     * 268. 丢失的数字
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int num = 0;
        for (int i = 0; i <= n; i++) {
            num ^= i;
        }
        for (int j : nums) {
            num ^= j;
        }
        return num;
    }

    /**
     * 1486. 数组异或操作
     *
     * @param n     n
     * @param start 开始
     * @return int
     */
    public int xorOperation(int n, int start) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum ^= (start + 2 * i);
        }
        return sum;
    }

    /**
     * 283. 移动零
     *
     * @param nums 全国矿工工会
     */
    public static void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 204. 计数质数
     *
     * @param n n
     * @return int
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimes1(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        // 从 2 开始枚举到 sqrt(n)。
        for (int i = 2; i * i < n; i++) {
            // 如果当前是素数
            if (isPrim[i]) {
                // 就把从 i*i 开始，i 的所有倍数都设置为 false。
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        // 计数
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 1009. 十进制整数的反码   476. 数字的补数
     *
     * @param n n
     * @return int
     */
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        long ans = 1;
        int tmp = 0;
        while (true) {
            if ((ans << tmp) > n) {
                break;
            }
            tmp++;
        }
        return ((int) (ans << tmp) - 1 - n);
    }

    /**
     * 1492. n 的第 k 个因子
     *
     * @param n n
     * @param k k
     * @return int
     */
    public int kthFactor(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        return list.size() < k ? -1 : list.get(k - 1);
    }

    /**
     * 2006. 差的绝对值为 K 的数对数目
     *
     * @param nums 全国矿工工会
     * @param k    k
     * @return int
     */
    public int countKDifference(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] - nums[j] == k || nums[i] - nums[j] == -k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 1929. 数组串联
     *
     * @param nums 全国矿工工会
     * @return {@link int[]}
     */
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i + n] = arr[i];
        }
        return arr;
    }

    /**
     * 2455. 可被三整除的偶数的平均值
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public int averageValue(int[] nums) {
        int sum = 0, count = 0;
        for (int num : nums) {
            if (num % 2 == 0 && num % 3 == 0) {
                sum += num;
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    /**
     * 2475. 数组中不等三元组的数目
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public static int unequalTriplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = i + 1; j <= n - 2; j++) {
                if (nums[i] != nums[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] != nums[k] && nums[j] != nums[k]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 2485. 找出中枢整数
     *
     * @param n n
     * @return int
     */
    public static int pivotInteger(int n) {
        int sum1 = 0;
        for (int i = 1; i <= n; i++) {
            int sum2 = 0;
            for (int j = i; j <= n; j++) {
                sum2 = sum2 + j;
            }
            sum1 = sum1 + i;
            if (sum1 == sum2) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 2544. 交替数字和
     *
     * @param n n
     * @return int
     */
    public static int alternateDigitSum(int n) {
        String s = String.valueOf(n);
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                sum = sum + (s.charAt(i) - '0');
            } else {
                sum = sum - (s.charAt(i) - '0');
            }
        }
        return sum;
    }

    /**
     * 1512. 好数对的数目
     *
     * @param nums 全国矿工工会
     * @return int
     */
    public static int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if ((Math.abs(arr[i] - arr[j]) <= a) && (Math.abs(arr[j] - arr[k]) <= b)
                            && (Math.abs(arr[i] - arr[k]) <= c)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 415. 字符串相加
     *
     * @param num1 num1
     * @param num2 num2
     * @return {@link String}
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0';
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    /**
     * 709. 转换成小写字母
     *
     * @param s 年代
     * @return {@link String}
     */
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }

    /**
     * 1281. 整数的各位积和之差
     *
     * @param n n
     * @return int
     */
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int sum1 = 1;
        if (n >= 10) {
            while (n > 0) {
                sum += n % 10;
                sum1 *= n % 10;
                n /= 10;
            }
            return sum1 - sum;
        }
        return 0;
    }

    /**
     * 989. 数组形式的整数加法
     *
     * @param num 全国矿工工会
     * @param k   k
     * @return {@link List}<{@link Integer}>
     */
    public static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<Integer>();
        int n = num.length;
        for (int i = n - 1; i >= 0 || k > 0; --i, k /= 10) {
            if (i >= 0) {
                k += num[i];
            }
            res.add(0, k % 10);
        }
        return res;
    }

    /**
     * 67. 二进制求和
     *
     * @param a
     * @param b
     * @return {@link String}
     */
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }

    /**
     * 1470. 重新排列数组
     *
     * @param nums 全国矿工工会
     * @param n    n
     * @return {@link int[]}
     */
    public int[] shuffle(int[] nums, int n) {
        int[] sum = new int[2 * n];
        for (int i = 0, j = 0, k = n; i < sum.length; i++) {
            if (i % 2 == 0) {
                sum[i] = nums[j];
                j++;
            } else {
                sum[i] = nums[k];
                k++;
            }
        }
        return sum;
    }

    /**
     * 867. 转置矩阵
     *
     * @param matrix 矩阵
     * @return {@link int[][]}
     */
    public int[][] transpose(int[][] matrix) {
        int[][] matrix1 = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix1[j][i] = matrix[i][j];
            }
        }
        return matrix1;
    }

    /**
     * 2586. 统计范围内的元音字符串数
     * @param words
     * @param left
     * @param right
     * @return int
     */
    public static int vowelStrings(String[] words, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            char c = words[i].charAt(0);
            char c1 = words[i].charAt(words[i].length() - 1);
            if ((c == 'a' || c == 'e' ||c == 'i' ||c == 'o' ||c == 'u')&&
                    (c1 == 'a' || c1 == 'e' ||c1 == 'i' ||c1 == 'o' ||c1 == 'u')){
                count++;
            }
        }
        return count;
    }

    /**
     * 852. 山脉数组的峰顶索引
     *
     * @param arr 加勒比海盗
     * @return int
     */
    public static int peakIndexInMountainArray(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.sort(Comparator.reverseOrder());
        Integer integer = list.get(0);
        List<Integer> list1 = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return list1.indexOf(integer);
    }

    /**
     * 1422. 分割字符串的最大得分
     *
     * @param s 年代
     * @return int
     */
    public int maxScore(String s) {
        return 0;
    }
    public static void main(String[] args) {
        int[] digits = new int[]{1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,0,6,3};
        //String[] words = new String[]{"vo","j","i","s","i"};
        //int[][] logs = new int[][]{{0, 10, 20}, {1, 20, 30}};
    }
}
