package others;

import java.util.*;

public class algo {
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
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] - nums[j] == k || nums[i] - nums[j] == -k){
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
        int arr[] = new int[n*2];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i+n] = arr[i];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{0, 1, 0, 3, 12};
//        String s = "aabcbc";
        //String s = "bg";
        //String s1 = "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj";
        int a = 92;
        int b = 70;
        moveZeroes(digits);
        //int[][] logs = new int[][]{{0, 10}, {1, 20}};
        //System.out.println(canConstruct(s, s1));
        //System.out.println(hardestWorker(10, logs));
    }
}
